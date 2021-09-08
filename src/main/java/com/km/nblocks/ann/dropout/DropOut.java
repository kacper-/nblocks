package com.km.nblocks.ann.dropout;

import java.io.Serializable;

public interface DropOut extends Serializable {
    double apply(double x);
}
