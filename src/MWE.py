#!/usr/bin/env python3
# -*- coding:utf-8 -*-

"""
purpose
   - to solve the problem Ax=b with A,b given

remark
   - values are set by hand to reduce dependencies

example
         16  -12    0
    A =   0   96  -48
          0    0   48

           -30
    b =   -360
          1240

           5.0
    x =   9.17
         25.83

source
    - https://www.youtube.com/watch?v=oqxPyRZKOu4
"""



from petsc4py import PETSc


N_POINTS = 3
A = PETSc.Mat().createAIJ([N_POINTS, N_POINTS])
A.setUp()
A.assemble()
A.setValue(0, 0, 16)
A.setValue(0, 1, -12)
A.setValue(0, 2, 0)
A.setValue(1, 0, 0)
A.setValue(1, 1, 96)
A.setValue(1, 2, -48)
A.setValue(2, 0, 0)
A.setValue(2, 1, 0)
A.setValue(2, 2, 48)
A.assemblyBegin()
A.assemblyEnd()
A.view()

b = PETSc.Vec().createSeq(N_POINTS)
b.setValue(0, -30)
b.setValue(1, -360)
b.setValue(2, 1240)
b.view()

# Allocate a PETSc vector storing the solution to the linear system
x = PETSc.Vec().createSeq(N_POINTS)
x.view()

# Instantiate a linear solver: Krylow subspace linear iterative solver
ksp = PETSc.KSP().create()
ksp.setOperators(A)
ksp.setFromOptions()
chosen_solver = ksp.getType()
print(f"Solving with {chosen_solver:}")
ksp.solve(b, x)
current_solution = x.getArray()
x.setArray(current_solution)
x.view()
