package ru.netology.nmedia.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.numberFormatting
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
): RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            postText.text = post.content
            published.text = post.published
            likesCnt.text = numberFormatting(post.likesCnt)
            shareCounter.text = numberFormatting(post.shareCnt)
            likesIcon.setImageResource(
                if (post.likedByMe) {
                    R.drawable.baseline_favorite_24
                } else {
                    R.drawable.baseline_favorite_border_24
                }
            )
            likesIcon.setOnClickListener {
                onLikeListener(post)
            }
            shareIcon.setOnClickListener {
                onShareListener(post)
            }
        }
    }
}
