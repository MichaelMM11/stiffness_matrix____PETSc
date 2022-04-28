#!/usr/bin/env python3
# -*- coding:utf-8 -*-

"""
purpose
    - to load a vector as PETSc object

worklfow
    1) execute the same code as 'load_matrix.py'
    2) take desired column (in most cases the 0th, because
    that's the way the values are stored)

requirement
    - matrix A must be given as matrix object
"""

b = A.getColumnVector(0)
b.view()
print(type(b))  # class 'petsc4py.PETSc.Vec'
