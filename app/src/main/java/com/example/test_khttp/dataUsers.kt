package com.example.test_khttp

class Users (val userID : String, val firstName : String, val lastName : String, val email : String, val avatar : String) {

    override fun toString(): String {
        return "userID: ${this.userID} \n" +
                "firstName: ${this.firstName} \n" +
                "lastName: ${this.lastName} \n" +
                "email: ${this.email}" +
                "avatar: ${this.avatar}"
    }
}

class Images() {

}

class dataUsers(val Users : List<Map<String, String>>) {
    override fun toString(): String {
        return "User: ${this.Users} \n"
    }
}