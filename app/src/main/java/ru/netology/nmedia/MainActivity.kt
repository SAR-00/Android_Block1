package ru.netology.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего. ",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            published = "21 марта в 19:47",
            likesCnt = 99999999,
            shareCnt = 9999
        )

        with(binding) {
            author.text = post.author
            postText.text = post.content
            published.text = post.published
            likesCnt.text = numberFormatting(post.likesCnt)
            shareCounter.text = numberFormatting(post.shareCnt)

            likesIcon.setOnClickListener {

                post.likedByMe = !post.likedByMe

                likesIcon.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.baseline_favorite_24
                    } else {
                        R.drawable.baseline_favorite_border_24
                    }
                )

                if (post.likedByMe) post.likesCnt++ else post.likesCnt--

                likesCnt.text = numberFormatting(post.likesCnt)

            }

            shareIcon.setOnClickListener {
                shareCounter.text = numberFormatting(++post.shareCnt)
            }
        }
    }

    private fun numberFormatting(cnt: Int): String {
        return when (cnt) {
            in 1000..9999 -> "${cnt.toString()[0]}.${cnt.toString()[1]}K"
            in 10_000..99_999 -> "${cnt.toString()[0]}${cnt.toString()[1]}K"
            in 100_000..999_999 -> "${cnt.toString()[0]}${cnt.toString()[1]}${cnt.toString()[2]}K"
            in 1_000_000..9_999_999 -> "${cnt.toString()[0]}.${cnt.toString()[1]}M"
            in 10_000_000..99_999_999 -> "${cnt.toString()[0]}${cnt.toString()[1]}M"
            in 100_000_000..999_999_999 -> "${cnt.toString()[0]}${cnt.toString()[1]}${cnt.toString()[2]}M"
            else -> "$cnt"
        }
    }

}