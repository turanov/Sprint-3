package ru.sber.oop

import java.util.Objects

data class User(val name: String, val age: Long) {
    lateinit var city: String

    override fun equals(other: Any?): Boolean {
        if (other !is User) return false
        return try {
            other.name == name && other.age == age && other.city == city
        } catch (e: UninitializedPropertyAccessException) {
            false
        }
    }

    override fun hashCode(): Int {
        return Objects.hash(name.hashCode(), age.hashCode(), city.hashCode())
    }
}

fun main() {
    val user1 = User("Alex", 13)
    val user2 = user1.copy(name = "ander")
    user1.city = "Omsk"
    val user3 = user1.copy()
    user3.city = "Tomsk"
    println(user1.equals(user3))
    println(user1.equals(user2))
    println(user2.equals(user3))
}