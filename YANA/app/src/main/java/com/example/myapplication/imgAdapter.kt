import android.view.LayoutInflater


import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import  com.example.myapplication.R

class ImageAdapter(private val imageList: ArrayList<Int>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var selectedItemPosition: Int = 0

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.slider_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vehilces_slider, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) { holder.imageView.setImageResource(imageList[position])
        //Apply border if the item is currently selected
//       if (position == selectedItemPosition) {
//         holder.imageView.setBackgroundResource(R.drawable.border_highlight) // Set your border drawable
//      } else {
//          holder.imageView.setBackgroundResource(android.R.color.transparent) // Remove border if not selected
//       }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun setSelectedItem(position: Int) {
        val previousSelectedItemPosition = selectedItemPosition
        selectedItemPosition = position
        notifyItemChanged(previousSelectedItemPosition)
        notifyItemChanged(selectedItemPosition)
    }

    init {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                // Calculate the position of the centered item based on the offset and notify the adapter
                val centeredPosition = if (positionOffset >= 0.5f) position + 1 else position
                setSelectedItem(centeredPosition)
            }
        })
    }
}