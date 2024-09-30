package ru.netology.nmedia.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        val adapter = PostsAdapter(
            {viewModel.likeById(it.id)},
            {viewModel.shareById(it.id)}
        )

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}

fun numberFormatting(cnt: Int): String {
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



