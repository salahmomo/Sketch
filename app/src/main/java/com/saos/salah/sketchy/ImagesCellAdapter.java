package com.saos.salah.sketchy;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.saos.salah.sketch.Sketch;
import com.saos.salah.sketch.listener.BitmapLoaderListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Salah on 25/10/2017.
 */

public class ImagesCellAdapter extends RecyclerView.Adapter<ImagesCellAdapter.ViewHolder> {
    private List<String> urls = Arrays.asList(
            "https://vignette.wikia.nocookie.net/finalfantasy/images/0/03/FFIX_Character_Height_Comparisons_1.jpg/revision/latest?cb=20130323090433",
            "https://i.warosu.org/data/tg/img/0331/39/1404343704696.png",
            "https://lh3.googleusercontent.com/YbC_i9FtdFcWNzYKoYj_j7JaujXNm9ivrzs2pcAdaN9_5S1OqsbWj8MCoKha0a-Ye1o=h900",
            "https://vignette.wikia.nocookie.net/finalfantasy/images/f/f3/Auron_Art.png/revision/latest/top-crop/width/320/height/320?cb=20141023194550",
            "http://vignette2.wikia.nocookie.net/finalfantasy/images/c/c6/FFXIII-Lightning_CG.png/revision/latest?cb=20150918214724",
            "https://vignette.wikia.nocookie.net/finalfantasy/images/4/4e/Snow.png/revision/latest/top-crop/width/320/height/320?cb=20150919185749",
            "https://vignette.wikia.nocookie.net/ssb/images/1/1a/Cloud-FFVIIArt.png/revision/latest?cb=20151112231859",
            "https://vignette.wikia.nocookie.net/finalfantasy/images/1/18/Scions_Anniversary.png/revision/latest/scale-to-width-down/350?cb=20140808125603",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJeV7rl_IFq0XvjNhWfxhgspABBWq2rckIYEox2cGW9-dZA2eF",
            "https://cdn1.twinfinite.net/wp-content/uploads/2017/02/FFXIV-Jobs.jpg",
            "https://i.pinimg.com/736x/f3/e6/ba/f3e6ba0785fae18ac602f43557de42a0--final-fantasy-cloud-final-fantasy-vii-remake.jpg",
            "https://vignette.wikia.nocookie.net/finalfantasy/images/4/41/-DoC-FFVII_VincentValentine.jpg/revision/latest/top-crop/width/480/height/480?cb=20080223071005",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9mM5jbrxxfOGtxT_4fXC2HGyxZYVO0LKlxhRmiDpjAsdWOfUU",
            "https://imagesvc.timeincapp.com/v3/fan/image?url=https://apptrigger.com/files/2016/12/final-fantasy-xv-monster-car.jpg&c=sc&w=850&h=560",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuAUyS1h-B4lq49a73Q7offh9DFUBaomoIew8D2vq2_lwOBC2m",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZWOi4yqNm6nRgzaQCRObpGWA5o4w-eMBBK9lxQCucmQ6E947tng",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSKm7gG5pjlPiyGrAqPeSp7I9BncQYKDzUq3gEWAbgmQiUjPdhF",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbVXbf5WfwpBXCw6p2p5BwOSjbFAfSMZv4ZxlR0Nv8HGg_GH9A",
            "https://vignette4.wikia.nocookie.net/finalfantasy/images/6/60/FF4TheAfterIllustration2.jpg/revision/latest?cb=20120707013849",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYSObNeAr7AzYGQ60lrDahEu4qOXhOKqXLL6kzVbXC0oZ659NSsg",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxSXkVlivla9HrJBrfuTD4LL9LCBE4RGNXxT-hWt_R0idCt5i8tQ",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTICXpTL47EqYN1B4pT6vs2TWQbEMKseGRBCPaCBso-lsEbFcESYw",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAiDgAaraGVZ_3vdekamnbFNSIdgefxoDaRoT3SVwddfCXlR5mpg",
"https://images-na.ssl-images-amazon.com/images/I/71Q4Wd1AViL.jpg",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4F7R7Lga8FCN4pUmSlsqHBz8VcopSy7Yy0ixHqxIsfilepOu0iQ",
"https://images.moviepilot.com/image/upload/c_fill,h_630,q_auto:best,w_1200/gwrqyhlq5htdkrm598ry.jpg",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSq-wgyJ-evYbchkxvXRhFbSf0RPfX8aDtTvNeof6cnvau8x_uy",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwgfwnaqdNr-jIz5cN4-Vj8wga8rEt-ueoVmiTrm9FNn3NNwi-Xg",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyr3H2Hc8776aCRDHgB1CwXGOQKUzWDksNYmtkXJKeM1S3Ogn25A",
"https://upload.wikimedia.org/wikipedia/en/8/8c/FFVIInomuracastdesigns.JPG",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbaNX8oGbi8mftzhQhjMr9W6S9mYClFLlEA_xyLxUMpBUxmdt56A",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEBYetCP37QTpeCFLAMxhpq7HDUZf-JO9syCwC6AWwDOA4GhKl",
"http://ekladata.com/CzchCDhZbSRqynfwZXcg_VUpayQ.jpg",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAG8otm4Mb7pLcKf63u9B-IcdS0qQqlX6x1NypEYAbk4chBHo3",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo-eEt8mZQRjQmWsgipy43jh1mrQoHTlqS22VmbhmTZV7kVVjF",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlaedlXCC6xhpXLtBkgpza3qbHlYHhxH25wUH3NFNiC6AIsOC96w",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtJsjXjsXlyWRxv1LewlIm79ncbHcR-LxtoKzh2SxymxRezDwg",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRl3IIkjTKQ7L_thofX6MKkbXQT6bD1ru-t-RbgqZ9hCttWlP8a",
"http://www.jeuxactu.com/datas/jeux/f/i/final-fantasy-xii-the-zodiac-age/vn/final-fantasy-xii-the-z-5900501c7295f.jpg",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9Ak1_T9g7aP6uAEqBbowJ1K7oCW4O16VcnmbED_UGFd8TnBjJ",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrFGDXKvtctQMDqUk25qhjh0R2t1WVC7CNwSHbbmhiY2HlQmaY_w",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0ikkTfUY7h51vEggZtZtLDWUikUlsLvOdjOuvnCjP7g03BeKucw",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIiTb_RM30Fm8o_Guj9IPPaRkZ7IKa_WLO7ar5N83UndSoLbPa",
"http://www.dlcompare.com/img/final-fantasy-8-img-4.jpg",
"http://cdn.edgecast.steamstatic.com/steam/apps/39150/header.jpg?t=1447359500",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyT7c9aCRHMv0Fe-FnZ9uPtAofV2kCDoue3zZSBRJQBVoV8ZbS",
"https://humblebundle.imgix.net/misc/files/hashed/3f2f967fd80a006e59a23c5b5c076a1965ca2d98.jpg?auto=format&fit=crop&h=353&w=616&ixlib=python-0.2.0&s=f31bb55d7e02c5c5ee3f09e0f0f5a01f",
"http://cdn.wccftech.com/wp-content/uploads/2015/11/Final.Fantasy.VI_.full_.1359222.jpg",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmVDyGt76E4Hu45nG8IcH9oto6ur5wgChHgcdUJV2GdZNjgyM9HA",
"https://static.giantbomb.com/uploads/original/16/163117/2698141-ffx-cast.jpeg",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWtEaCmTVA9cZj6ZzCrEsqIQ94phUgEv75ZqxZ4FRy68dydi0c",
            "https://static.giantbomb.com/uploads/original/16/163117/2698141-ffx-cast.jpeg",
            "https://static.giantbomb.com/uploads/original/16/163117/2698141-ffx-cast.jpeg",
            "https://static.giantbomb.com/uploads/original/16/163117/2698141-ffx-cast.jpeg",
            "https://static.giantbomb.com/uploads/original/16/163117/2698141-ffx-cast.jpeg",
            "https://static.giantbomb.com/uploads/original/16/163117/2698141-ffx-cast.jpeg",
            "https://static.giantbomb.com/uploads/original/16/163117/2698141-ffx-cast.jpeg",
            "https://static.giantbomb.com/uploads/original/16/163117/2698141-ffx-cast.jpeg",
            "https://upload.wikimedia.org/wikipedia/en/0/00/Final_Fantasy_V_Box_JAP.jpg"
    );

    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.imageView);
        }
    }
    public ImagesCellAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
/*        Sketch.getInstance().initCacheWithDefaultSize(context).loadImage(urls.get(position), new BitmapLoaderListener() {
            @Override
            public void onFinish(Bitmap bitmap) {
                holder.image.setImageBitmap(bitmap);
            }

            @Override
            public void onCancel() {
                holder.image.setImageBitmap(null);
            }
        });*/
        Sketch.getInstance().initCacheWithDefaultSize(context).loadImageIntoImageView(urls.get(position), holder.image);
        //(new DownloadImageTask(holder.image)).execute(urls.get(position));
        //Picasso.with(context).load(urls.get(position)).into(holder.image);
        //MainActivity.imageLoader.displayImage(urls.get(position),holder.image , MainActivity.displayImageOptions);
//        holder.image.setImageResource(R.drawable.foot);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }
}
