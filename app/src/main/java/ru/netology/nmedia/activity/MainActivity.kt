package ru.netology.nmedia.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.focusAndShowKeyboard
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        val adapter = PostsAdapter(object: OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }
        })

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            val newPost = posts.size > adapter.currentList.size && adapter.currentList.isNotEmpty()
            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(0)
                }
            }
        }

        viewModel.edited.observe(this) {
            if (it.id != 0L) {
                binding.content.setText(it.content)
                binding.content.focusAndShowKeyboard()
                binding.postTextEdit.text = it.content
                binding.editGroup.visibility = View.VISIBLE
            }
        }

        binding.close.setOnClickListener {
            with(binding.content) {
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }

            viewModel.clearEdited()
            binding.editGroup.visibility = View.INVISIBLE
        }

        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Content can't be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.applyChangesAndSave(text.toString())

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.editGroup.visibility = View.INVISIBLE
            }
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



