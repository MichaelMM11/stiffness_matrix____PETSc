#!/usr/bin/env python3
# -*- coding:utf-8 -*-

"""
purpose
   - to demonstrate how a matrix can be built with AIJ method

remark
    - if this is an acceptable way to construct large matrices is not decided
    - three different vectors has to be loaded (cannot be loaded as a matrix as one 0s have a meaning)
    - one possible way to circumvent is to set all elements that carry not a number with 'nan'
    - but then matrix can still be dense and therefore not convenient to deal with

source
    - https://bitbucket.org/petsc/petsc4py/issues/67/createaijwitharrays-in-parallel
"""

from petsc4py import PETSc



# to initialize data
comm=PETSc.COMM_WORLD
rank = comm.rank
size = comm.size
I = [0, 2, 5, 8, 11, 14, 16]
J = [0, 1, 0, 1, 2, 1, 2, 3, 2, 3, 4, 3, 4, 5, 4, 5]
A = [2, -1, -1, 2, -1, -1, 2, -1, -1, 2, -1, -1, 2, -1, -1, 2]


# to create and inspect matrix
M = PETSc.Mat()
M.createAIJWithArrays([6, 6], (I,J,A), bsize=None, comm=comm)
M.view()
