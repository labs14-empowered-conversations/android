package com.lambdaschool.empoweredconversation

data class Token(val access_token: String?,
                val token_type: String?,
                val refresh_token: String?,
                val expires_in: Number?,
                val scope: String?)
