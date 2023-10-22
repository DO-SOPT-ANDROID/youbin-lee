package org.sopt.dosopttemplate.data

sealed class FriendSealed {

    data class MyProfile(
        val name: String,
        val profileImage: Int,
    ) : FriendSealed()

    data class FriendProfile(
        val name: String,
        val self_description: String?,
        val profileImage: Int,
    ) : FriendSealed()

//    data class Music(
//        val name: String,
//        val self_description: String?,
//        val profileImage: Int,
//        val music: String
//    ) : FriendSealed()
//
//    data class Birthday(
//        val name: String,
//        val self_description: String?,
//        val profileImage: Int,
//    ) : FriendSealed()

}