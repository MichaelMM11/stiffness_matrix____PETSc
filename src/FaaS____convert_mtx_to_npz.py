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

from pathlib import Path
from scipy.io import mmread

import scipy

# to set directory dependencies
current__dir = Path.cwd()
main__dir = current__dir.parents[0]
data__dir = main__dir.joinpath('data/test')


# to select stiffness matrix, load vector
matrix_filename = '3x1_vector____test'
suffix_to_load = '.mtx'
suffix_to_export = '.npz'


# set files
matrix_to_load = Path(data__dir, matrix_filename).with_suffix(suffix_to_load)
matrix_to_export = Path(data__dir, matrix_filename).with_suffix(suffix_to_export)


# these are the important lines
matrix_test = mmread(matrix_to_load)
scipy.sparse.save_npz(matrix_to_export, matrix_test)


# to load exported matrix
A = scipy.sparse.load_npz(matrix_to_export)
