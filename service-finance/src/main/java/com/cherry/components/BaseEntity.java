package com.cherry.components;

public interface BaseEntity<E extends BaseEntity<E>> {
    E replace(E newEntity);
}