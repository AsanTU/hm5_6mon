package mbk.io.hm5_m6.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import mbk.io.hm5_m6.databinding.ItemTaskBinding
import mbk.io.hm5_m6.model.TaskModel

class TaskAdapter(
    private val onLongClickItem: (task: TaskModel) -> Unit,
    private val onClick: (task: TaskModel) -> Unit
) : ListAdapter<TaskModel, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(tasks: TaskModel) = with(binding) {
            tvTitle.text = tasks.title
            tvDescription.text = tasks.description
            itemView.setOnLongClickListener {
                onLongClickItem(tasks)
                true
            }

            itemView.setOnClickListener {
                onClick(tasks)
            }
        }
    }
    class TaskDiffCallback : DiffUtil.ItemCallback<TaskModel>() {

        override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem == newItem
        }
    }
}