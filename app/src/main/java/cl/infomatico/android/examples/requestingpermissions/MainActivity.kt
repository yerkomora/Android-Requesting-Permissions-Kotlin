package cl.infomatico.android.examples.requestingpermissions

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bRecord.setOnClickListener { recordPermissionsCheck() }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_RECORD -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    record()
                } else {
                }
                return
            }
        }
    }

    // MainActivity

    companion object {
        const val PERMISSIONS_RECORD = 1
    }

    private fun recordPermissionsCheck() {

        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.RECORD_AUDIO)) {

                val builder = AlertDialog.Builder(this)

                builder.setMessage(R.string.permission_load)
                builder.setTitle(R.string.permission_required)
                builder.setPositiveButton(R.string.ok) { _, _ -> recordPermissionsMake() }

                builder.create().show()
            } else {
                recordPermissionsMake()
            }
        } else {
            record()
        }
    }

    private fun recordPermissionsMake() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                PERMISSIONS_RECORD)
    }

    private fun record() {
    }
}