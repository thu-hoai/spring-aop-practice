package com.example.demo.jdbc;

class Bird {
// ...
  double getSpeed(BirdType type) {
    switch (type) {
      case EUROPEAN:
        return getBaseSpeed();
      case AFRICAN:
        return getBaseSpeed() - getLoadFactor() * numberOfCoconuts;
      case NORWEGIAN_BLUE:
        return (isNailed) ? 0 : getBaseSpeed(voltage);
    }
    throw new RuntimeException("Should be unreachable");
  }
}

enum BirdType {

  EUROPEAN,
  NORWEGIAN_BLUE,

  AFRICAN;
}