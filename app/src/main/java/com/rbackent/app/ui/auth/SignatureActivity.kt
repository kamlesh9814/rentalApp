package com.rbackent.app.ui.auth

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.gcacace.signaturepad.views.SignaturePad
import com.rbackent.app.R
import com.rbackent.app.databinding.ActivitySignatureAvtivityBinding
import com.rbackent.app.ui.sharedpreferences.AppPrefs
import com.rbackent.app.ui.utils.ConstantsVar
import java.io.ByteArrayOutputStream
class SignatureActivity() : AppCompatActivity() {
    var additional = ""
    var type = ""
    lateinit var binding: ActivitySignatureAvtivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signature_avtivity)
        getIntentData()
        onClick()
    }

    private fun getIntentData() {
        additional = intent.getStringExtra("additional")!!
        type = intent.getStringExtra("houseSignature")!!

    }

    private fun onClick() {
        binding.signaturePad.setOnClickListener {
            binding.signaturePad.setOnSignedListener(object : SignaturePad.OnSignedListener {
                override fun onStartSigning() {

                }

                override fun onSigned() {


                }

                override fun onClear() {

                }

            })
        }
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.IVSubmit.setOnClickListener {
            if (type == ConstantsVar.APARTMENT) {
                if (additional == "signature") {
                    val signatureBitmap: Bitmap = binding.signaturePad.signatureBitmap
                    val stream = ByteArrayOutputStream()
                    signatureBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    val byte = stream.toByteArray()
                    val encodedImage =
                        android.util.Base64.encodeToString(byte, android.util.Base64.DEFAULT)
                   AppPrefs(this@SignatureActivity).setString("signatureImage", encodedImage)
                    onBackPressed()

                } else if (additional == "signature1") {
                    val signatureBitmap: Bitmap = binding.signaturePad.signatureBitmap
                    val stream = ByteArrayOutputStream()
                    signatureBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    val byte = stream.toByteArray()
                    val encodedImage =
                        android.util.Base64.encodeToString(byte, android.util.Base64.DEFAULT)
                    AppPrefs(this@SignatureActivity).setString("imageSignature", encodedImage)
                    onBackPressed()
                }
            } else if (type == ConstantsVar.HOUSE){
                if (additional == "signature") {
                    val signatureBitmap: Bitmap = binding.signaturePad.signatureBitmap
                    val stream = ByteArrayOutputStream()
                    signatureBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    val byte = stream.toByteArray()
                    val encodedImage =
                        android.util.Base64.encodeToString(byte, android.util.Base64.DEFAULT)
                    AppPrefs(this@SignatureActivity).setString("house-signatureImage", encodedImage)
                    onBackPressed()

                } else if (additional == "signature1") {
                    val signatureBitmap: Bitmap = binding.signaturePad.signatureBitmap
                    val stream = ByteArrayOutputStream()
                    signatureBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    val byte = stream.toByteArray()
                    val encodedImage =
                        android.util.Base64.encodeToString(byte, android.util.Base64.DEFAULT)
                    AppPrefs(this@SignatureActivity).setString("house-imageSignature", encodedImage)
                    onBackPressed()
                }

            }
        }
    }
}
