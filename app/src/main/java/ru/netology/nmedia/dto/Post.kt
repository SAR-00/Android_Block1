package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likesCnt: Int = 0,
    val likedByMe: Boolean = false,
    val shareCnt: Int = 0
)