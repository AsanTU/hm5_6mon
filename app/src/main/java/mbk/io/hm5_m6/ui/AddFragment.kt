package mbk.io.hm5_m6.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mbk.io.hm5_m6.App
import mbk.io.hm5_m6.R
import mbk.io.hm5_m6.databinding.FragmentAddBinding
import mbk.io.hm5_m6.model.TaskModel

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task = arguments?.getSerializable(MainFragment.TASK_EDIT_KEY) as TaskModel?
        if (task != null) {
            binding.btnSave.text = "Update"
            binding.etTitle.setText(task.title)
            binding.etDescription.setText(task.description)
        }
        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text.toString().isNotEmpty()) {
                if (task != null) {
                    update(task)
                } else save()
                findNavController().navigateUp()
            } else binding.etTitle.error = "Enter Title"
        }
    }

    private fun update(task: TaskModel) {
        App.db.taskDao().update(
            task.copy(
                title = binding.etTitle.text.toString(),
                description = binding.etDescription.text.toString()
            )
        )
    }
    private fun save(){
        val data = TaskModel(
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString()
        )
        App.db.taskDao().insert(data)
    }

}