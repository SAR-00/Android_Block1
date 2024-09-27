package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl: PostRepository {

    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего. ",
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        published = "21 марта в 19:47",
        likesCnt = 99999999,
        shareCnt = 9999
    )

    private val data = MutableLiveData(post)

    override fun get() = data

    override fun like() {
        post = post.copy(
            likedByMe = !post.likedByMe,
            likesCnt = if (post.likedByMe) post.likesCnt - 1 else post.likesCnt + 1
        )
        data.value = post
    }

    override fun share() {
        post = post.copy(shareCnt = post.shareCnt + 1)
        data.value = post
    }
}