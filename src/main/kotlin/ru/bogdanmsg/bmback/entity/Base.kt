package ru.bogdanmsg.bmback.entity

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import java.util.*

@MappedSuperclass
abstract class Base {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    val id: UUID = UUID.randomUUID()

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Base

        return id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    override fun toString(): String {
        return "${this.javaClass.simpleName}(id=$id)"
    }
}
