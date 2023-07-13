package com.example.server

import com.example.data.Images
import com.example.data.VenueTest
import com.google.gson.Gson
import java.util.Calendar
import kotlin.random.Random

object MockDataHelper {

    fun getListOfVenuesJson(): String {
        val listOfVenues = createListOfVenues()

        return Gson().toJson(listOfVenues)
    }


    private fun createListOfVenues(): ArrayList<VenueTest> {
        val listOfVenues = arrayListOf<VenueTest>()
        val imageUrls = getImageUrls()

        val name = "Ocean Drive Miami"
        var distance = 0
        val location = "Belgard Road, Tallaght Miami"
        val welcomeMessage = "Welcome to Poke Bar"
        val description = "Poke Bar makes it easy to customize your bowl with endless toppings, proteins, mix-in and more."

        for (i in 0..49) {

            // distance
            distance += Random.nextInt(50,300)
            val distanceText = if (distance >= 1000) "${roundTheNumber(distance / 1000f)}km" else "${distance}m"

            // working hours
            val startWorkingTime = Random.nextInt(6,20)
            val endWorkingTime = Random.nextInt(startWorkingTime + 1,24)
            val workingTimeText = "${if (startWorkingTime < 10) "0$startWorkingTime" else "$startWorkingTime"}:00 - ${if (endWorkingTime == 24) "00" else "$endWorkingTime" }:00"

            val currentHours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            //Log.d("currentHoursTest", "currentHours: $currentHours")

            val venue = VenueTest(
                "$name ${i + 1}",
                distanceText,
                "${i + 1} $location",
                workingTimeText,
                currentHours in startWorkingTime until endWorkingTime,
                welcomeMessage,
                description,
                Images(imageUrls[i % 9])
            )

            listOfVenues.add(venue)

        }

        return listOfVenues
    }

    private fun roundTheNumber(numInDouble: Float): String {
        return "%.2f".format(numInDouble)
    }

    private fun getImageUrls(): ArrayList<String> {

        val listOfUrls = arrayListOf<String>()

        listOfUrls.add("https://uccaa9b49136deec87ffe77b45f6.previews.dropboxusercontent.com/p/thumb/AB9RzMQwI7ak_WJnp0RfDusVbEx0Jj-HrVaVT_kiFXLXkfFePmYCKQJ5IxmTVZn9d9dQNlAbscfg6145sxk2HsK_Piv6WKs_9TiVoMz795-uJZYk4lYF3CNh_FhVnMWxsL0jflovy4Hkk_zTJySZ2HqOV0k8LHGlS6uKNJTsP8OkIkb66wpOAbIKmmcP6t5dlM9czrNYmQSUt3TFJndH1IYLCLRiv8V4adX00tnCf0xpYB-PpqX-S6u-5kXZ5YpDsgq0QzGoy3vGiikdNRXbOpSRInl9BkioE4iCh8gePJOt55BVKLXnPTVxb5zHbUosyr42aHirfqeVcqf0Gj2VQvPwneXNI6OBjyIE6hSlJZ17m8-KE_C2PxiMFnpRWWsd7W0/p.jpeg")
        listOfUrls.add("https://ucf2bdfabcb7da4163ddf6f0b12c.previews.dropboxusercontent.com/p/thumb/AB_oWUjvJVEP-nPfCgKJbAsXG3VQ05d_B4gkeVX_dSh6WlB2JqnwvZgXN8R2HhCNnVVMolEHXeaecSlZKMiVUfmfs2l_yU-Cl5AdHcvjIWJejoSEwp2ZlpLL407CDw-WzRPpksYm3YX2SqhnqyVx9gb_vMcmNI_s23EaRUBnwFFxjytaGax3jDkMqvYUioGsBvK57vyN7zqudaycx2wIkVDfh96Du-iOJVZ56owxKJfbGvb0Ps-L_4nHeG5m1YGnmdUGnZZJhXZbntm6M1lAA3WlvrlK7gN2isT1KwXkipL-wkvdGFtm0jHn1bYmfDDT6GmpMOwCGV84s6II-UCeDaKF3tNkq8giwfyE4oX4J-B_QV7ZTdkwJkPvmRVfcGq0OD19bMcx0Gqza_j0ktGlYBgqGZOKjDiJpWJLpyhvQ19-3AFRAy7hNktau4ilktskeN4/p.jpeg")
        listOfUrls.add("https://uc242546069d45cef180cac7c79a.previews.dropboxusercontent.com/p/thumb/AB_VjHd_VGobdizkID3Ga3M2Q3ahPSs-lpFFoXiDAXI5FJQBNXhhFJ4OFDTqdWFMaPHrkBHt51g_22dyg1kH_0BD1xDyjgXvo-vBVHYdaE5Am5ER6EUHrCPT_D3224jD8lTJVUfbmy05RIFAsrmH5j4U-h1V6tObnr5vJinhHsasFBh1WQqg29-BoZAeHaZ8aPJresuYCh7vcpWn7kM4vfeckPw1Dyvp6w7-K2COu76yOonnNEUinweLFd3mYMsN60pegitTbdxrao1Bwet9ux-g7bFZzX719dzPKMLfakM--3G96u1OTpkZ00uShbRxVWs3bdUp9TiiA_NrH4fYwR136KE2hJboxj7a9fUvFHVC-AHi2PbwMaquGfIXuDmhkzSv4QMyvUqzWq7HpnAzoRUtyhN6xw8t9ONf2R7gvSj-6Bqxkh7vv6F02RLHUQN_pW_WPzxjxKyKbodeflbL17VaGWeFwCEBj74bYGulWoeHo0CkJ2ujq3rx1zZMGgPhfMA/p.jpeg")
        listOfUrls.add("https://uc33abbb6e5dc594f456f8ac96c4.previews.dropboxusercontent.com/p/thumb/AB_Hk_thkF80MuI4qfnlZt9OLiFrMB_6M2s2QLBp6VyUE_t3wDoADAQtWUlWaKqyItLRN27WBjZY9wwwY-axB3NC8q-xVkEgdy93VuG6M9SaPlMUCsGTpcUFbH1KRtA623N3DFoZVK94jMRKI3wrST0BXVgGt2UHPNJKCkkBe-U7VOl7MRHygf1yREbhhcDKQmCv0cmnyTlHVu9FtnZ1iWn5orCaKXDiomIUEGGpvTJnbW10MIz7p9xQ7odmKbjThQ4ODI3_MADc2QXaLbsff8HI4OOllL21HKUzZlgtreKNeNfX6mfQde34NtnJHH8iH0xknfx1_7bVjjD2aWhix9pHdTAmbccPAtkPeDaPeEowiKQQV9Bqfls1ZLEHJHaPFxkZ1_t-SsZru3zty-LdscJCC4tvpQRQsxtW5AODQ6QbDO6VSsqkMoPe1JGoqzKNl6WuklCEEem_tbnikIc2NFEh4gqQCqbiKX87Q6NjLEucZ0z3Lnxde2mgSdurgxloVKU/p.jpeg")
        listOfUrls.add("https://uc64a996931a2b7e6f8a9b999a81.previews.dropboxusercontent.com/p/thumb/AB9aCsxpO33AvtxBvPJD2ihSUmBgkkHIyWJG1JthFLtK4h9sfEHOW1v-fZTxNN4-WsgJR5zukkkO2Yb903xmZJJxOiz38IAy_NRzr0ek68GpKQyZeU9eMg4KrEvYlz3JPdOP8DGO31Tu9OwECqKItTwhbeFGoPzk59ojflKWBiJdK-qS8SqlreQOv5hdcHCehPjLjlU3EzstfxthjIUwIKuECaATIoPBbqCdoPy-D4EFzvcwCaAbu56aRfqWwcdArh9n6lFHXTBqGJuYGdyDFAhz_jXMq_z1RHPUHgv4I2ruzdbUjO_KAhTJfYmN7tmOrQDId4utika4Tie-VUYYnEzCdKYkbFv__V8N4VwaxPzwiwwehOZk1cfpykOAPbWTq6o/p.jpeg")
        listOfUrls.add("https://uc17d2d4642e8dde12c415c577e7.previews.dropboxusercontent.com/p/thumb/AB8-T3SJH4rbgLUzBwaRERWIMaf2oDXXxd5DxAlFoWwj0SFOG1I4unVsBCDgpl_fGmF0gUsJ0NCd09J-zFvNwbu2iAW4tD5cv-UIz_LO6X_pqvAjHmOYYIIEraelEdu8-XU5SxaqS_hKejhIQEJRXdp-oWujNTEOwzadBxaWqGfO-8DJOVCw1HRwcxw2gHimkdOncq9weJ6mbEffw5M564lZZo6dMkSfVRX9OFw-j2JAa1JZSOEFVAkefWdVnaR5PAFCiDyEzzt9YNAwQiv7XYyI7J2Q4PfmPmbzkhgH6DhJKFWb0XR3c5HEDAysy1Czi2-TdRRKrT92udWNJOwZgpsMvUALoiw4sS7cuxqvR1ByGoVZTyIA-fLZSfDC8ntXqJ4uGaVuuVBhvcriA6otLrVy_SEb66RAraaPk6Vhnd4wEFoM25s3l9_DpjBkXItputknbKbg1_14P3_tscOgBD0VJB430ROUsKrQdwvstmngb5rN5QDuyPCAk7sR32toFRI/p.jpeg")
        listOfUrls.add("https://uc1916286a9d0d24e76857d8071e.previews.dropboxusercontent.com/p/thumb/AB9CvhCEtso438Ttx1396OFoRZN9HGGbYr5coB2B-o3G5YXt2sn7xG2JVzHdJOHB030PeuKeDNfhMInuT3qZ24xJ6C-enlVTiGxz3ycpvO-W4iLls-07j5kV20CfA_9Yf4CsDyTq3dFLgBnQr824kj8INHmt14hj208iytNGkoDGkdIypzQ8qN99ZJVSn7ikgjXKazjEkrnsGJefewos-vLmEkuaRhHTlSaF7jqJdZjtgzn5ePhCfRbXH2LnQvfFCFSKptLznhllhe8NHvNS-uBuBI3x0hwSlJBqiz4LfS9Tq-C-iJ77q0ekd-LzgJ0aooOOCMh-S0fxpWWdcjGC0N-70KBVfXmaL88Jtacrfajg8d5dy-DOCWmCGWhtuUPXcXYRV9CcSjC3GfICfj4yDqO18W4ZSCz4ogNfQCWBr9FbBzocsSwMRnNJVWXkv9byVjY/p.jpeg")
        listOfUrls.add("https://uc04f4b129bba5cbdab5d26def06.previews.dropboxusercontent.com/p/thumb/AB-MpmnaJcLjTKw0ByQ6xujfSEARH0C2ecV__-LdtxN8tIJsMY6jb0d73nKpwCUGwdcoIlCGvsa-iR6n9VP8zR8WM1vJpgpwLdVB7iINnE8sMw8rZLNcVlzZ2X1phiIk5IM7G8zRF9JiZRKxcezmqJecjOD8tJzCLNyiKPxZOj-MjI99lQx6pAfXlwEK66EMr9fHm5aKhD3nIv2UheerwccDZnrWKbSNQT27YhtQRi4Zj8f63910D9PpNo85vHNUqCH0o1X22bGgg6EjDl5RR9mv6KkM54Fs6nVoSmsWFZMfJ_S8vfmi71BBHEO-06mYPM3rCq2PIs0OL8eMxdEi-QeSOmKDw3r3h7VRCo_6Bxf9frRzMHW4kVTiFyQtzN1uiug/p.jpeg")
        listOfUrls.add("https://uc5b2aabf1f95e7c5d718c083772.previews.dropboxusercontent.com/p/thumb/AB8tXFy2X8O8fjB7dbMkDodX2Byu1fUkSezDXsp2g3gx85CPhg2kZ3Y36xaygW9ZGwIhnOpadLxPTWfJJaedam0F0bZi0EHWlmbMcrIdMOIgRIE3kFhJR5eFX_cqq5EJHNXfR0LPz4XTUtDyqKJAW4fEoo7olDB8ubAT603atxxUOVUYYV1fj_6uGACdOHF1GkcEiyzkt3f4-Ss0gnHHkUso1OhDBFDR-C-fEhDeo_laU3HZXY7oM3Quegx2pydibvQa4L5t4VjR3eifB26DiH-nGFFw8TCiWENrA4mAFYsFz6HgzykDffUeIiA62WuyYyKJetfS7W8_anrfgdfDjtU5bXotu1XTd4hZFY5ds-U98hoCgpaX9GNf0LIbFzYPb4I/p.jpeg")

        return listOfUrls

    }

}