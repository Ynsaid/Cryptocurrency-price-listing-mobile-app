package com.example.cryptocurrencyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val cryptoList: List<Crypto>) :RecyclerView.Adapter<RecyclerViewAdapter.CryptoHolderView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.CryptoHolderView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return CryptoHolderView(view)
    }

    class CryptoHolderView(view: View) :RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.cryptoImage)
        val name: TextView = view.findViewById(R.id.cryptoName)
        val symbol: TextView = view.findViewById(R.id.cryptoSymbol)
        val price: TextView = view.findViewById(R.id.cryptoPrice)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.CryptoHolderView, position: Int) {
        val crypto = cryptoList[position]
        holder.name.text = crypto.name
        holder.symbol.text = crypto.symbol
        holder.price.text = "$${crypto.price_usd}"
        val iconResId = getIconForCrypto(crypto.symbol)
        holder.icon.setImageResource(iconResId)
    }

    private fun getIconForCrypto(symbol: String): Int {
        return when (symbol.lowercase()){
            "btc" -> R.drawable.bitcoin
            "eth" -> R.drawable.ethereum
            "xrp" -> R.drawable.coins
            "usdt" -> R.drawable.usdt
            "bnb" -> R.drawable.bnb
            "sol" -> R.drawable.sol
            "usdc" -> R.drawable.usdc

            else -> R.drawable.currency
        }
    }

    override fun getItemCount(): Int = cryptoList.size

}