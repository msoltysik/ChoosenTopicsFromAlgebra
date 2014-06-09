package algorithms;
import algorithms.interfaces.IGenerator;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WellEquidistributedLongperiodLinear extends RandomStreamBase {

   private static final long serialVersionUID = 70510L;
   private static final double NORM = (1.0 / 0x100000001L);

   private static final int W = 32;
   private static final int R = 16;
   private static final int P = 0;
   private static final int M1 = 13;
   private static final int M2 = 9;
   private static final int M3 = 5;
   private static final int MASK = 0xF; // = 15

   //state variables
   private int state_i;
   private int[] state;

   //stream and substream variables :
   private int[] stream;
   private int[] substream;
   private static int[] curr_stream;

   //the state transition matrices
   private static BitMatrix Apw;
   private static BitMatrix Apz;

   // if the generator was initialised
   private static boolean initialised = false;

   private static final int [] pw = new int[]
                          {0x280009a9, 0x31e221d0, 0xa00c0296, 0x763d492b,
                           0x63875b75, 0xef2acc3a, 0x1400839f, 0x5e0c8526,
                            0x514e11b, 0x56b398e4, 0x9436c8b9, 0xa6d8130b,
                           0xc0a48a78, 0x26ad57d0, 0xa3a0c62a, 0x3ff16c9b};

   private static final int [] pz = new int[]
                          {0xcd68f2fe, 0x183e969a, 0x760449ae, 0xaa0ce54e,
                           0xfb5363af, 0x79deea9b, 0xef66c516, 0x103543cb,
                           0x244d1a97, 0x7570bc91, 0x31203fc7, 0x455ea2ca,
                           0xd77d327d, 0xd8c6a83c, 0xc51b05e7, 0x300c1501};

   private static void initialisation() {

      curr_stream = new int[] {0xA341BF9A, 0xAFE4901B, 0x6B10DE18, 0x05FE1420,
                               0xE48B1A9C, 0x590AE15E, 0xC5EB82A7, 0x37EAB2F9,
                               0x90E1C6EA, 0x3AE63902, 0x735DC91C, 0x902E3A8C,
                               0x6CB28A5D, 0x8474E7D1, 0x843E01A3, 0x5A7370EF};

      initialised = true;
   

   private void advanceSeed(int[] seed, int [] p) {
      int b;
      int [] x = new int[R];

      for (int i = 0; i < R; i++) {
         state[i] = seed[i];
      }
      state_i = 0;

      for (int j = 0; j < R; ++j) {
         b = p[j];
         for (int k = 0; k < W; ++k) {
            if ((b & 1) == 1) {
               for (int i = 0; i < R; i++) {
                  x[i] ^= state[(state_i + i) & MASK];
               }
            }
            b >>= 1;

            nextValue();
         }
      }

      for (int i = 0; i < R; i++) {
         seed[i] = x[i];
      }
   }


   private static void verifySeed(int[] seed) {
      if (seed.length < R)
         throw new IllegalArgumentException("Seed must contain " + R +
                                            "values");

      for (int i = 0; i < R; i++)
         if (seed[i] != 0)
            return;

      throw new IllegalArgumentException
      ("At least one of the element of the seed must not be 0.");
   }

   private WellEquidistributedLongperiodLinear(int i) {
      state = new int[R];
      for(int j = 0; j < R; j++)
         state[j] = 0;
      state[i / W] = 1 << (i % W);
      state_i = 0;
   }

   public WellEquidistributedLongperiodLinear() {
      if (!initialised)
         initialisation();

      state = new int[R];
      stream = new int[R];
      substream = new int[R];

      for(int i = 0; i < R; i++)
         stream[i] = curr_stream[i];

      advanceSeed(curr_stream, pz);

      resetStartStream();
   }

   public WellEquidistributedLongperiodLinear (String name) {
      this();
      this.name = name;
   }

   public static void setPackageSeed (int seed[]) {
      verifySeed(seed);
      if (!initialised)
         initialisation();

      for(int i = 0; i < R; i++)
         curr_stream[i] = seed[i];
   }

   public void setSeed (int seed[]) {
      verifySeed(seed);

      for(int i = 0; i < R; i++)
         stream[i] = seed[i];

      resetStartStream();
   }
   public int[] getState() {
      int[] result = new int[R];
      for(int i = 0; i < R; i++)
         result[i] = state[(state_i + i) & MASK];
      return result;
   }

   public WellEquidistributedLongperiodLinear clone() {
      WELL512 retour = null;

      retour = (WELL512)super.clone();
      retour.state = new int[R];
      retour.substream = new int[R];
      retour.stream = new int[R];

      for (int i = 0; i<R; i++) {
         retour.state[i] = state[i];
         retour.substream[i] = substream[i];
         retour.stream[i] = stream[i];
      }
      return retour;
   }

  
   public void resetStartStream() {
      for(int i = 0; i < R; i++)
         substream[i] = stream[i];
      resetStartSubstream();
   }

   public void resetStartSubstream() {
      state_i = 0;
      for(int i = 0; i < R; i++)
         state[i] = substream[i];
   }

   public void resetNextSubstream() {
      advanceSeed(substream, pw);
      resetStartSubstream();
   }

   public String toString() {
      StringBuffer sb = new StringBuffer();

      if(name == null)
         sb.append("The state of this WELL512 is : {");
      else
         sb.append("The state of " + name + " is : {");
      for(int i = 0; i < R - 1; i++)
         sb.append(state[(state_i + i) & MASK] + ", ");
      sb.append(state[(state_i + R - 1) & MASK] + "}");

      return sb.toString();
   }

   protected double nextValue() {
      int z0, z1, z2;
      z0 = state[(state_i + 15) & MASK];
      z1 = (state[state_i] ^ (state[state_i] << 16)) ^
           (state[(state_i+M1) & MASK] ^ (state[(state_i+M1) & MASK] << 15));
      z2 = (state[(state_i+M2) & MASK] ^
           (state[(state_i+M2) & MASK] >>> 11));
      state[state_i] = z1 ^ z2;
      state[(state_i + 15) & MASK] = (z0 ^ (z0 << 2)) ^ (z1 ^ (z1 << 18)) ^
                       (z2 << 28) ^ (state[state_i] ^
                           ((state[state_i] << 5) & 0xDA442D24));
      state_i = (state_i + 15) & MASK;

      long result = state[state_i];

      return (double)(result > 0 ? result : (result + 0x100000000L)) * NORM;
   }

}
