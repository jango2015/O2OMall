package itlwy.com.o2omall.fragment;

import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import itlwy.com.o2omall.ConstantValue;
import itlwy.com.o2omall.R;
import itlwy.com.o2omall.base.BaseApplication;
import itlwy.com.o2omall.base.BaseFragment;
import itlwy.com.o2omall.base.BaseHolder;
import itlwy.com.o2omall.bean.Product;
import itlwy.com.o2omall.view.CirclePageIndicator;

/**
 * Created by Administrator on 2016/2/23.
 */
public class ProductFragment extends BaseFragment<Product, Product> {
    @Override
    public Product load() {
        Product pro = getArguments().getParcelable(Product.Tag);
        return pro;
    }

    @Override
    public View createSuccessView() {
        return new ProductHolder(getActivity()).getContentView();
    }

    @Override
    protected String getFragmentKey() {
        return ConstantValue.PRODUCTFRAGMENT;
    }

    public class ProductHolder extends BaseHolder<Product, Object> {
        @Bind(R.id.product_viewPager)
        AutoScrollViewPager productViewPager;
        @Bind(R.id.product_indicator)
        CirclePageIndicator productIndicator;
        @Bind(R.id.pd_btn_collect)
        ImageButton pdBtnCollect;
        @Bind(R.id.pd_btn_addIntoCart)
        Button pdBtnAddIntoCart;
        @Bind(R.id.pd_btn_cart)
        ImageButton pdBtnCart;
        private boolean collected;
        private PagerAdapter pagerAdapter;
        private List<View> viewContainer;
        private List<String> urls;

        /**
         * 广告自动循环切换的时间间隔
         */
        private static final int AUTO_SCROLL_INTERVAL = 3000;

        public ProductHolder(Context ctx) {
            super(ctx);
        }

        @Override
        public View initView() {
            View view = View.inflate(getContext(), R.layout.fragment_product_details, null);
            ButterKnife.bind(this, view);
            return view;
        }

        @Override
        public void refreshView(Product product) {
            getViewImage();
            pagerAdapter = new PagerAdapter() {

                @Override
                public boolean isViewFromObject(View arg0, Object arg1) {
                    return arg0 == arg1;
                }

                @Override
                public int getCount() {
                    return viewContainer.size();
                }

                @Override
                public void destroyItem(View container, int position, Object object) {
                    ((AutoScrollViewPager) container).removeView(viewContainer
                            .get(position));
                }

                @Override
                public Object instantiateItem(View container, int position) {
                    ((AutoScrollViewPager) container).addView(viewContainer
                            .get(position));
                    return viewContainer.get(position);
                }

            };

            productViewPager.setAdapter(pagerAdapter);
            productIndicator.setViewPager(productViewPager);
            productViewPager.setInterval(AUTO_SCROLL_INTERVAL);
            productViewPager.startAutoScroll();
        }

        @OnClick({R.id.pd_btn_collect,R.id.pd_btn_addIntoCart,R.id.pd_btn_cart})
        public void btnClicked(View view){
            switch (view.getId()) {
                case R.id.pd_btn_collect:
                    if (!collected) {
                        ((TransitionDrawable) pdBtnCollect.getDrawable())
                                .startTransition(200);
                        collected = true;
                    } else {
                        ((TransitionDrawable) pdBtnCollect.getDrawable())
                                .reverseTransition(200);
                        collected = false;
                    }

                    break;

                default:
//                    ProductDetailsThirdLayerActivity.this.finish();
                    break;
            }
        }

        /**
         * 获取广告图片列表
         */
        private void getViewImage() {
            if (viewContainer == null) {
                viewContainer = new ArrayList<View>();
            }
            if (urls == null) {
                urls = new ArrayList<String>();
            }
            urls = getImageUrls();
            viewContainer.clear();
            for (String url : urls) {
                ImageView iv = new ImageView(getActivity());
                ImageLoader.getInstance().displayImage(url, iv,
                        ((BaseApplication) getActivity().getApplication()).getOptions());
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                viewContainer.add(iv);
            }
        }
        /**
         * 获取广告位的图片资源url数组
         * 每次从服务器获得url数组先做永久性存储，获取时先从本地显示之前的缓存，等获取成功之后再调用notifyDataSetChanged()
         *
         * @return url数组
         */
        private ArrayList<String> getImageUrls() {
            ArrayList<String> list = new ArrayList<String>();
            // //////////////////////////////////////
            // ////////////////假数据/////////////////
            list.add("http://b.hiphotos.baidu.com/image/pic/item/14ce36d3d539b6006bae3d86ea50352ac65cb79a.jpg");
            list.add("http://c.hiphotos.baidu.com/image/pic/item/37d12f2eb9389b503564d2638635e5dde7116e63.jpg");
            list.add("http://g.hiphotos.baidu.com/image/pic/item/cf1b9d16fdfaaf517578b38e8f5494eef01f7a63.jpg");
            list.add("http://f.hiphotos.baidu.com/image/pic/item/77094b36acaf2eddce917bd88e1001e93901939a.jpg");
            list.add("http://g.hiphotos.baidu.com/image/pic/item/f703738da97739124dd7b750fb198618367ae20a.jpg");
            // //////////////////////////////////////
            return list;
        }
    }
}