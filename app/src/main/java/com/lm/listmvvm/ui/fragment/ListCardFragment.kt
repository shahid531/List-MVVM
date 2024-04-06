package com.lm.listmvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lm.listmvvm.databinding.FragmentLayoutRvBinding
import com.lm.listmvvm.error.Resource
import com.lm.listmvvm.model.DataItem
import com.lm.listmvvm.ui.adapter.ListCardAdapter
import com.lm.listmvvm.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCardFragment() : Fragment() {
    private val viewmodel by viewModels<ListViewModel>()
    private lateinit var listCardAdapter: ListCardAdapter
    private var _binding: FragmentLayoutRvBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLayoutRvBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        viewmodel.getData()
    }

    private fun observe() {
        viewmodel.listData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    binding.loader.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.loader.visibility = View.GONE
                    it.data?.data?.let { response ->
                        setAdapter(response as List<DataItem>)
                    }
                }
            }
        }
    }

    private fun setAdapter(list: List<DataItem>) {
        listCardAdapter = ListCardAdapter(requireContext(), list)
        val listCardManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.listRv.apply {
            adapter = listCardAdapter
            layoutManager = listCardManager
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}