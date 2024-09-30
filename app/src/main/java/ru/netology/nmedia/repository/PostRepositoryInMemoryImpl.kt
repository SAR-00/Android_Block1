package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl: PostRepository {

    private var posts = listOf(
        Post(
            id = 4,
            author = "Нетология. Университет интернет-профессий будущего. ",
            content = "Purus nisi class dolor vestibulum sit imperdiet duis egestas. Sapien lacinia netus interdum volutpat mauris dignissim vestibulum finibus. Odio rhoncus quisque ut primis diam sit magnis ipsum elit. Nisi placerat tempor turpis pulvinar pharetra amet sociosqu. Netus mauris blandit turpis metus sociosqu sit leo. Himenaeos mi penatibus nostra est sit leo litora semper. Bibendum imperdiet malesuada et taciti habitant.",
            published = "24 марта в 21:12",
            likesCnt = 851,
            shareCnt = 28
        ),
        Post(
            id = 3,
            author = "Нетология. Университет интернет-профессий будущего. ",
            content = "Hendrerit semper ac suscipit ut suscipit nisl consequat efficitur. Cras lacinia tempor auctor morbi netus sociosqu id elit pulvinar. Magnis proin aliquet netus; class et at. Lectus magnis a feugiat hac enim dolor sagittis. Auctor facilisis sodales fusce nisi ridiculus eget dignissim arcu. Tempus habitant eu pharetra netus natoque tempor taciti. Adipiscing lacinia rutrum hac proin finibus ad elit. Sit tortor scelerisque urna bibendum; tincidunt bibendum.",
            published = "23 марта в 11:05",
            likesCnt = 1500,
            shareCnt = 100
        ),
        Post(
            id = 2,
            author = "Нетология. Университет интернет-профессий будущего. ",
            content = "Condimentum ad elementum curae accumsan porttitor? Efficitur fringilla inceptos ultrices nisi faucibus dolor. Torquent senectus fusce molestie elementum quis sapien magnis fusce viverra. Vel malesuada maecenas sed taciti et orci metus. Lobortis accumsan aptent; augue rutrum consequat iaculis purus. Proin est varius cursus finibus euismod varius euismod at viverra.",
            published = "22 марта в 22:22",
            likesCnt = 12_234,
            shareCnt = 344
        ),
        Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего. ",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            published = "21 марта в 19:47",
            likesCnt = 99_999_999,
            shareCnt = 9999
        )
    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(likedByMe = !it.likedByMe, likesCnt = it.likesCnt + if (it.likedByMe) -1 else 1)
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(shareCnt = it.shareCnt + 1)
        }
        data.value = posts
    }
}