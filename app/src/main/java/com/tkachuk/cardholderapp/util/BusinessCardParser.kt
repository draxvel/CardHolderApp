package com.tkachuk.cardholderapp.util

object BusinessCardParser {
    fun parse (myList: MutableList<String>): Map<String, String> {
        val info: MutableMap<String, String> = mutableMapOf()
        val phone = findPhoneNumber(myList)
        val email = findEmail(myList)
        info["phone"] = phone
        info["email"] = email
        for(item in myList){
            if(item!=phone && item!=email){
                info["null"] = item
            }
        }
        return info
    }

    private fun findPhoneNumber(words: List<String>): String {
        var number = "No valid phoner number found"
        for (i in words.indices) {
            //if phone number format includes -, . , spaces, + sign in front
            if (words[i].replace("[^\\d.]".toRegex(), "").matches("[+]?\\d?[- .]?(\\([0-9]\\d{2}\\)|[0-9]\\d{2})[- .]?\\d{3}[- .]?\\d{4}$".toRegex())) {
                number = words[i].replace("[^\\d.]".toRegex(), "").replace("-", "").replace(".", "").replace(" ", "").replace("(", "").replace(")", "").replace("+", "")
                break
            }
        }
        return number
    }

    private fun findEmail(words: List<String>): String {
        var email = "No valid email found"
        for (i in words.indices) {
            // if email has words in beginning, dot is optional, followed by @ and domain plus .com,.edu,etc (min length of 2 char)
            if (words[i].matches(("^[\\w\\+]+(\\.[\\w]+)*@" + "[\\w]+(\\.[\\w]+)*(\\.[A-Za-z]{2,})$").toRegex())) {
                email = words[i]
                break
            }
        }
        return email
    }
}