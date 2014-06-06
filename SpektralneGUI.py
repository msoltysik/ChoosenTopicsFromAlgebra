#!/bin/python2
# -*- coding: UTF-8 -*-
from os import remove
import subprocess

from PySide import QtGui, QtCore
from pylab import suptitle, ylim, xlim, scatter, show


class SpektralneGUI(QtGui.QWidget):
    def __init__(self):
        super(SpektralneGUI, self).__init__()
        self.init_ui()

    def init_ui(self):
        algorithm_label = QtGui.QLabel('Algorithm: ')
        maximum_value_label = QtGui.QLabel('Maximum value: ')
        test_numbers_label = QtGui.QLabel('Number of tests')
        plot_button = QtGui.QPushButton("Plot!", self)
        exit_button = QtGui.QPushButton("Exit", self)
        self.reviewEdit = QtGui.QLineEdit("10")
        self.authorEdit = QtGui.QLineEdit("10")
        self.titleEdit = QtGui.QListWidget(self)

        self.titleEdit.addItems(
            ["BlumBlumShub", "LinearCongruentialGenerator", "LinearFeedbackShiftRegister", "MersenneTwister",
             "MultiplyWithCarry", "ParkMiller", "Wichman_Hill", "Xorshift", "RC4PRGA",
             ])
        self.titleEdit.setCurrentRow(0)

        my_grid = QtGui.QGridLayout()
        my_grid.setSpacing(10)

        my_grid.addWidget(algorithm_label, 1, 0)
        my_grid.addWidget(self.titleEdit, 1, 1)

        my_grid.addWidget(maximum_value_label, 2, 0)
        my_grid.addWidget(self.authorEdit, 2, 1)

        my_grid.addWidget(test_numbers_label, 3, 0)
        my_grid.addWidget(self.reviewEdit, 3, 1)

        my_grid.addWidget(plot_button, 4, 0)
        my_grid.addWidget(exit_button, 4, 1)

        plot_button.clicked.connect(self.ok_button_clicked)
        exit_button.clicked.connect(QtCore.QCoreApplication.instance().quit)

        self.setLayout(my_grid)
        self.center()
        self.setFixedSize(350, 235)
        self.setWindowTitle('Wybrane zagadnienia algebry')

    def ok_button_clicked(self):
        algorytm = self.titleEdit.currentItem().text()
        max_v = self.authorEdit.text()
        n = self.reviewEdit.text()
        if int(n) < 0 or int(max_v) < 0:
            self.warning_message()
        else:
            self.plot_graphic(algorytm, max_v, n)

    def warning_message(self):
        QtGui.QMessageBox.about(self, "Warning", u"Argumenty nie mogą być mniejsze od 0.")

    def plot_graphic(self, algorytm, max_v, n):
        subprocess.call(['java', '-jar', 'PRNG.jar', algorytm, max_v, n, '2'])
        f = open('temp.txt', 'r')
        file_readed = f.read()
        f.close()
        file_readed = file_readed.split("\n")
        x, y = [], []
        for i in range(len(file_readed) - 1):
            file_readed[i] = file_readed[i].split(",")
            x.append(file_readed[i][0])
            y.append(file_readed[i][1])

        suptitle(self.titleEdit.currentItem().text() + u", p = %d" % int(n), fontsize=20)
        ylim([0, int(max_v)])
        xlim([0, int(max_v)])
        scatter(x, y, c='black', alpha=0.1)
        show()
        remove("temp.txt")

    def keyPressEvent(self, e):
        if e.key() == QtCore.Qt.Key_Escape:
            self.close()
        if e.key() == QtCore.Qt.Key_Return:
            self.ok_button_clicked()

    def center(self):
        frame_geometry = self.frameGeometry()
        cp = QtGui.QDesktopWidget().availableGeometry().center()
        frame_geometry.moveCenter(cp)
        self.move(frame_geometry.topLeft())


if __name__ == "__main__":
    import sys

    app = QtGui.QApplication(sys.argv)
    m = SpektralneGUI()
    m.show()
    exit(app.exec_())
