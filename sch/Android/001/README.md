# Resource
- https://www.iconfinder.com/icons/386451/arch_linux_archlinux_icon

# Reference

## Design
- https://youtu.be/O17fvn7kztg?si=uACikGYlFsePHb-r # Profile
- https://youtu.be/XMI1iejmtzs?si=5WzLT188RYHXzDLe # Login/Register

## Layout
- https://stackoverflow.com/questions/45776994/android-gridlayout-columnspan-not-spanning
- https://stackoverflow.com/questions/2710793/what-is-the-equivalent-of-colspan-in-an-android-tablelayout # no

## Code
### Intent
- https://developer.android.com/guide/components/intents-common#java # Intent Android
- https://www.geeksforgeeks.org/how-to-make-a-phone-call-from-an-android-application/
- https://stackoverflow.com/questions/68808796/how-can-you-launch-termux-through-another-android-app
- https://stackoverflow.com/questions/2201917/
- https://developer.android.com/reference/android/net/MailTo # mailto
- https://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application # Intent with url
- https://stackoverflow.com/questions/3872063/how-to-launch-an-activity-from-another-application-in-android # Intent with package name
- https://stackoverflow.com/questions/2662531/launching-google-maps-directions-via-an-intent-on-android # Intent Maps
### Configuration
- https://stackoverflow.com/questions/3631982/change-applications-starting-activity # Change starting activity

# -
## Design
### Satuan
- dp(Density-independent Pixels) -> Konsisten pada setiap ukuran layar.
- sp(Scale-independent Pixels)   -> Menyesuaikan dengan ukuran layar.

### Styles
#### -
Semisal saya memiliki beberapa/banyak edit text seperti dibawah 
```xml
<EditText
    android:id="@+id/username"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="20dp"
    android:background="@drawable/edittext"
    android:drawableLeft="@drawable/icon_username"
    android:drawablePadding="8dp"
    android:hint="Username"
    android:inputType="text|textAutoComplete"
    android:padding="10dp"
    android:textColor="@color/black"
    android:textColorHint="#8D8D8D" />

<EditText
    android:id="@+id/password"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="20dp"
    android:background="@drawable/edittext"
    android:drawableLeft="@drawable/icon_password"
    android:drawablePadding="8dp"
    android:hint="Password"
    android:inputType="text|textPassword"
    android:padding="10dp"
    android:textColor="@color/black"
    android:textColorHint="#8D8D8D" />
```

Jika diamati, terdapat banyak pengulangan code.
Hal ini tidak sesuai dengan perinsip [DRY](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself).

Hal lain yang menjadi potensi masalah adalah menjaga konsistensi antara satu sama lain.

Hal ini dapat diatasi dengan mendefinisikan sebuah/beberapa style pada file `res/values/styles.xml`.

Contoh isi file `styles.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="basic_input">
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:layout_marginTop">20dp</item>
        <item name="android:padding">10dp</item>
        <item name="android:textColor">#000</item>
        <item name="android:textColorHint">#8D8D8D</item>
        <item name="android:drawablePadding">8dp</item>
        <item name="android:background">@drawable/edittext</item>
    </style>
    
    <style name="other_style">...</style>
<resources>
```

Untuk menerapaknnya cukup dengan menambah atribut `style` pada tiap tag. Contoh
```xml
<EditText
    android:id="@+id/username"
    android:hint="Username"
    android:inputType="text|textAutoComplete"
    style="@style/basic_input" />

<EditText
    android:id="@+id/password"
    android:hint="Password"
    android:inputType="text|textPassword"
    style="@style/basic_input" />
```

#### -
Semisal ada sebuah style seperti berikut
```xml
<style name="main_info_text">
    <item name="android:layout_width">wrap_content</item>
    <item name="android:layout_height">wrap_content</item>
    <item name="android:textStyle">bold</item>
    <item name="android:textColor">@color/white</item>
</style>
```

Kemudian saya ingin membuat style baru yang mewarisi `main_info_text` dengan beberapa penambahan
```xml
<style name="main_info_text_value">
    <item name="android:textSize">10sp</item>
</style>
```

Agar `main_info_text_value` mewarisi `main_info_text`, tambahkan `parent` sehingga menjadi
```xml
<style name="main_info_text_value" parent="main_info_text">
    <item name="android:textSize">10sp</item>
</style>
```

### LinearLayout
#### Attribute
- `android:gravity`         -> Mengatur elemen didalam layout/child.
- `android:layout_gravity`  -> Mengatur LinearLayout itu sendiri.

### Fungsi CardView
- Memberi Bayangan -> `app:cardElevation`
- Memberi Radius   -> `app:cardCornerRadius`

### Fungsi LinearLayout yang berisi TextView, View, dan Button
Fungsi utamanya adalah agar teks register dan button dapat bersebelahan.
Hal ini dilakukan dengan `android:orientation="horizontal"`.

Jika menggunakan RelativeLayout, hal ini dapat dilakukan hanya dengan `android:layout_toRightOf`.

## Code
### Mengganti activity yang pertama kali dibuka
Ketika aplikasi baru dijalankan/dibuka, activity yang pertama kali dijalankan adalah MainActivity.
Hal tersebut dapat diubah dengan konfigurasi pada AndroidManifest.

AndroidManifest default:
```xml
<activity
    android:name=".MainActivity"
    android:exported="true" >
        <intent-filter>/
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
</activity>
```

Jika ingin mengganti ke Activity lain dapat dilakukan dengan memindahnya.

```xml
<activity
    android:name=".LoginActivity"
    android:exported="false" >
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

Jangan lupa ubah MainActivity menjadi seperti berikut agar tidak terjadi kesalahan yang tidak terduga
```xml
<activity
    android:name=".MainActivity"
    android:exported="true" />
```
Jika ketika run, LoginActivity tidak secara otomatis terbuka, ubah `android:exported` menjadi `true`.

### Lambda
Pada sebelum sebelumnya, intent dilakukan dengan menggunakan _Anonymous classes_ seperti pada code dibawah

```java
findViewById(R.id.btn_youtube).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent YouTube = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/"));
        startActivity(YouTube);
    }
});
```

Hal tersebut sebenarnya dapat dipersingkat dengan _Lambda Expression_
```java
findViewById(R.id.btn_github).setOnClickListener(v -> {
    Intent Github = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Aspiann17"));
    startActivity(Github);
});
```

### Intent
Ada beberapa cara untuk melakukan intent.
1. Menggunakan atribut pada tag.
2. 