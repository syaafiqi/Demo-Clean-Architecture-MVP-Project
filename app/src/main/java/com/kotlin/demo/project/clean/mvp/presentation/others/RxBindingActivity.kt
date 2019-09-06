package com.kotlin.demo.project.clean.mvp.presentation.others

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.kotlin.demo.project.clean.mvp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_rxbinding.*
import java.util.concurrent.TimeUnit

class RxBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxbinding)

        val disposables = CompositeDisposable()

        val emailValidation = et_email.textChanges()
            .skipInitialValue()
            .debounce(300, TimeUnit.MILLISECONDS)
            .retry()
            .map { email -> PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {email ->
                if (!email) emailLayout.error = "Email tidak valid!"
                else emailLayout.error = null
            }

        val passwordValidation = et_password.textChanges()
            .skipInitialValue()
            .debounce(300, TimeUnit.MILLISECONDS)
            .retry()
            .map { password -> password.length >= 6 }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {password ->
                if (!password) passwordLayout.error = "Password kurang dari 6 karakter!"
                else passwordLayout.error = null
            }

        Observables.combineLatest(emailValidation, passwordValidation) { email, password ->
            email && password
        }.startWith(false)
            .subscribe { isValid -> buttonSubmit.isEnabled = isValid }
            .addTo(disposables)

        buttonSubmit.clicks()
            .subscribe {
                Toast.makeText(this, "Login...", Toast.LENGTH_SHORT).show()
            }
            .addTo(disposables)
    }
}