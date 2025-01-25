package com.tnn.component;

public interface BaseEntity<E extends BaseEntity<E>> {
    E replace(E newEntity);
}