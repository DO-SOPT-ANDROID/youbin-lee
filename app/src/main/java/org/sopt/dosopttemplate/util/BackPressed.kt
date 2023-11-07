import android.content.Context
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.util.shortToast

fun AppCompatActivity.doubleBackPressed() {
    val sharedPreferences = getSharedPreferences("double_back_pressed", Context.MODE_PRIVATE)
    var initTime = sharedPreferences.getLong("init_time", 0)

    this.onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 3초 이내에 백 버튼을 누르면 종료
            if (System.currentTimeMillis() - initTime > 3000) {
                shortToast("뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.")
                initTime = System.currentTimeMillis()
            } else {
                Intent(Intent.ACTION_MAIN).apply {
                    addCategory(Intent.CATEGORY_HOME)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(this)
                }
            }
        }
    })
}