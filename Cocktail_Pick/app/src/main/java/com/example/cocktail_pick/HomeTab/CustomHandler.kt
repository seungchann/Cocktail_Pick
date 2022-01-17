package com.example.cocktail_pick.HomeTab

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cocktail_pick.R
import com.example.cocktail_pick.databinding.DialogCustomBinding
import com.example.cocktail_pick.databinding.ItemCustomImageBinding

class CustomHandler {
    fun setGlass(glass: String, ice: Int, garnishFirst: String, garnishSecond: String, color: String, binding: ItemCustomImageBinding){
        when(glass){
            "칵테일 글라스" -> {
                binding.glass.setImageResource(R.drawable.glass_cocktail)
                binding.liquid.setImageResource(R.drawable.liquid_cocktail)
                setIce(glass, ice, binding)
                setGarnishFirst(glass, garnishFirst, binding)
                setGarnishSecond(glass, garnishSecond, binding)
            }
            "소서형 샴폐인" -> {
                binding.glass.setImageResource(R.drawable.glass_champagne)
                binding.liquid.setImageResource(R.drawable.liquid_champagne)
                setIce(glass, ice, binding)
                setGarnishFirst(glass, garnishFirst, binding)
                setGarnishSecond(glass, garnishSecond, binding)
            }
            "사워 글라스" -> {
                binding.glass.setImageResource(R.drawable.glass_sour)
                binding.liquid.setImageResource(R.drawable.liquid_sour)
                setIce(glass, ice, binding)
                setGarnishFirst(glass, garnishFirst, binding)
                setGarnishSecond(glass, garnishSecond, binding)
            }
            "풋티스 필스너" -> {
                binding.glass.setImageResource(R.drawable.glass_pilsner)
                binding.liquid.setImageResource(R.drawable.liquid_pilsner)
                setIce(glass, ice, binding)
                setGarnishFirst(glass, garnishFirst, binding)
                setGarnishSecond(glass, garnishSecond, binding)
            }
            "셰리 와인 글라스" -> {
                binding.glass.setImageResource(R.drawable.glass_sherry_wine)
                binding.liquid.setImageResource(R.drawable.liquid_sherry_wine)
                setIce(glass, ice, binding)
                setGarnishFirst(glass, garnishFirst, binding)
                setGarnishSecond(glass, garnishSecond, binding)
            }
            "와인 글라스" -> {
                binding.glass.setImageResource(R.drawable.glass_wine)
                binding.liquid.setImageResource(R.drawable.liquid_wine)
                setIce(glass, ice, binding)
                setGarnishFirst(glass, garnishFirst, binding)
                setGarnishSecond(glass, garnishSecond, binding)
            }
            "리큐르 글라스" -> {
                binding.glass.setImageResource(R.drawable.glass_liqueur)
                binding.liquid.setImageResource(R.drawable.liquid_liqueur)
                setIce(glass, ice, binding)
                setGarnishFirst(glass, garnishFirst, binding)
                setGarnishSecond(glass, garnishSecond, binding)
            }
            "올드 패션 글라스" -> {
                binding.glass.setImageResource(R.drawable.glass_old_fashion)
                binding.liquid.setImageResource(R.drawable.liquid_old_fashion)
                setIce(glass, ice, binding)
                setGarnishFirst(glass, garnishFirst, binding)
                setGarnishSecond(glass, garnishSecond, binding)
            }
            "하이볼 글라스" -> {
                binding.glass.setImageResource(R.drawable.glass_highball)
                binding.liquid.setImageResource(R.drawable.liquid_highball)
                setIce(glass, ice, binding)
                setGarnishFirst(glass, garnishFirst, binding)
                setGarnishSecond(glass, garnishSecond, binding)
            }
            "탐 콜린스 글라스" -> {
                binding.glass.setImageResource(R.drawable.glass_tom_callins)
                binding.liquid.setImageResource(R.drawable.liquid_tom_callins)
                setIce(glass, ice, binding)
                setGarnishFirst(glass, garnishFirst, binding)
                setGarnishSecond(glass, garnishSecond, binding)
            }
        }
        binding.liquid.setColorFilter(Color.parseColor(color))
    }

    fun setIce(glass: String, ice: Int, binding: ItemCustomImageBinding){
        var iceView = binding.ice
        when (glass){
            "칵테일 글라스", "소서형 샴폐인" -> {
                when(ice){
                    0 -> iceView.setImageResource(R.drawable.ice_pieces_cocktail_champagne)
                    1 -> iceView.setImageResource(R.drawable.ice_circle_cocktail_champagne)
                    2 -> iceView.setImageResource(R.drawable.ice_square_cocktail_champagne)
                }
            }
            "사워 글라스", "리큐르 글라스", "와인 글라스", "셰리 와인 글라스", "풋티스 필스너" -> {
                when(ice){
                    0 -> iceView.setImageResource(R.drawable.ice_pieces_liqueur_wine_sherry_pilsner_sour)
                    1 -> iceView.setImageResource(R.drawable.ice_circle_liqueur_wine_sherry_pilsner_sour)
                    2 -> iceView.setImageResource(R.drawable.ice_square_liqueur_wine_sherry_pilsner_sour)
                }
            }
            "올드 패션 글라스", "탐 콜린스 글라스", "하이볼 글라스" -> {
                when(ice){
                    0 -> iceView.setImageResource(R.drawable.ice_pieces_tom_collins_highball_old_fashion)
                    1 -> iceView.setImageResource(R.drawable.ice_circle_tom_collins_highball_old_fashion)
                    2 -> iceView.setImageResource(R.drawable.ice_square_tom_collins_highball_old_fashion)
                }
            }
        }
    }

    fun setGarnishFirst(glass: String, garnishFirst: String, binding: ItemCustomImageBinding){
        val garnishView = binding.garnishFirst
        when(glass){
            "칵테일 글라스", "소서형 샴폐인" -> {
                when(garnishFirst){
                    "체리" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_cocktail_champagne)
                    }
                    "허브" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_cocktail_champagne)
                    }
                    "오렌지" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_cocktail_champagne)
                    }
                    "자몽" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_cocktail_champagne)
                    }
                    "라임" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_cocktail_champagne)
                    }
                    "x" -> {
                        garnishView.visibility= View.INVISIBLE
                    }
                }
            }
            "사워 글라스", "리큐르 글라스", "풋티스 필스너", "와인 글라스", "셰리 와인 글라스" -> {
                when(garnishFirst){
                    "체리" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_liqueur_wine_sherry_pilsner_sour)
                    }
                    "허브" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_liqueur_wine_sherry_pilsner_sour)
                    }
                    "오렌지" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_liqueur_wine_sherry_pilsner_sour)
                    }
                    "자몽" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_liqueur_wine_sherry_pilsner_sour)
                    }
                    "라임" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_liqueur_wine_sherry_pilsner_sour)
                    }
                    "x" -> {
                        garnishView.visibility= View.INVISIBLE
                    }
                }
            }

            "올드 패션 글라스" -> {
                when(garnishFirst){
                    "체리" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_old_fashion)
                    }
                    "허브" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_old_fashion)
                    }
                    "오렌지" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_old_fashion)
                    }
                    "자몽" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_old_fashion)
                    }
                    "라임" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_old_fashion)
                    }
                    "x" -> {
                        garnishView.visibility= View.INVISIBLE
                    }
                }
            }
            "하이볼 글라스" -> {
                when(garnishFirst){
                    "체리" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_highball)
                    }
                    "허브" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_highball_tom_collins)
                    }
                    "오렌지" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_highball_tom_collins)
                    }
                    "자몽" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_highball_tom_collins)
                    }
                    "라임" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_highball_tom_collins)
                    }
                    "x" -> {
                        garnishView.visibility= View.INVISIBLE
                    }
                }
            }
            "탐 콜린스 글라스" -> {
                when(garnishFirst){
                    "체리" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_tom_collins)
                    }
                    "허브" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_highball_tom_collins)
                    }
                    "오렌지" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_highball_tom_collins)
                    }
                    "자몽" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_highball_tom_collins)
                    }
                    "라임" -> {
                        garnishView.visibility= View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_highball_tom_collins)
                    }
                    "x" -> {
                        garnishView.visibility= View.INVISIBLE
                    }
                }
            }
        }
    }

    fun setGarnishSecond(glass: String, garnishSecond: String, binding: ItemCustomImageBinding) {
        val garnishView = binding.garnishSecond
        when (glass) {
            "칵테일 글라스", "소서형 샴폐인" -> {
                when (garnishSecond) {
                    "체리" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_cocktail_champagne)
                    }
                    "허브" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_cocktail_champagne)
                    }
                    "오렌지" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_cocktail_champagne)
                    }
                    "자몽" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_cocktail_champagne)
                    }
                    "라임" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_cocktail_champagne)
                    }
                    "x" -> {
                        garnishView.visibility = View.INVISIBLE
                    }
                }
            }
            "사워 글라스", "리큐르 글라스", "풋티스 필스너", "와인 글라스", "셰리 와인 글라스" -> {
                when (garnishSecond) {
                    "체리" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_liqueur_wine_sherry_pilsner_sour)
                    }
                    "허브" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_liqueur_wine_sherry_pilsner_sour)
                    }
                    "오렌지" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_liqueur_wine_sherry_pilsner_sour)
                    }
                    "자몽" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_liqueur_wine_sherry_pilsner_sour)
                    }
                    "라임" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_liqueur_wine_sherry_pilsner_sour)
                    }
                    "x" -> {
                        garnishView.visibility = View.INVISIBLE
                    }
                }
            }

            "올드 패션 글라스" -> {
                when (garnishSecond) {
                    "체리" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_old_fashion)
                    }
                    "허브" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_old_fashion)
                    }
                    "오렌지" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_old_fashion)
                    }
                    "자몽" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_old_fashion)
                    }
                    "라임" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_old_fashion)
                    }
                    "x" -> {
                        garnishView.visibility = View.INVISIBLE
                    }
                }
            }
            "하이볼 글라스" -> {
                when (garnishSecond) {
                    "체리" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_highball)
                    }
                    "허브" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_highball_tom_collins)
                    }
                    "오렌지" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_highball_tom_collins)
                    }
                    "자몽" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_highball_tom_collins)
                    }
                    "라임" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_highball_tom_collins)
                    }
                    "x" -> {
                        garnishView.visibility = View.INVISIBLE
                    }
                }
            }
            "탐 콜린스 글라스" -> {
                when (garnishSecond) {
                    "체리" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_cherry_tom_collins)
                    }
                    "허브" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_hurb_highball_tom_collins)
                    }
                    "오렌지" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_orange_highball_tom_collins)
                    }
                    "자몽" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_zamong_highball_tom_collins)
                    }
                    "라임" -> {
                        garnishView.visibility = View.VISIBLE
                        garnishView.setImageResource(R.drawable.garnish_lime_highball_tom_collins)
                    }
                    "x" -> {
                        garnishView.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

}