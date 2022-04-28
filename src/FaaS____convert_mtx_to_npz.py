#!/usr/bin/env python3
# -*- coding:utf-8 -*-

"""
purpose
   - to convert a given .mtx file into a .npz file

requirement
    - file must be given in .mtx format

remark
    - file will be saved as .npz format
    - for later usage how to load the file is given

source
    - https://stackoverflow.com/questions/46065418/how-do-i-write-a-script-to-read-a-matrix-from-a-mtx-file-using-the-function-scip
"""

from scipy.io import mmread
import scipy

matrix_test = mmread('3x1_vector_test.mtx')
scipy.sparse.save_npz('3x1_vector_test.npz', matrix_test)


#A = scipy.sparse.load_npz('file_to_save.npz')
