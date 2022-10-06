package ru.alexrojer31.tzinch.kernel.abstractions;

import org.springframework.data.repository.CrudRepository;

public interface Repository<T extends Essence> extends CrudRepository<T, Long> {
}
