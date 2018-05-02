package br.com.mobway.agendai.data

data class User(
    var uid: String?,
    var email: String?,
    var name: String?,
    var gender: String?,
    var loginType: String?,
    var phone: String?,
    var isFirstAccess: Boolean?,
    var isEmailVerified: Boolean?,
    var fcmToken: String?,
    var photoUrl: String?
)