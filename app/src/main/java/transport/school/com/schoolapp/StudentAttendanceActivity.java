package transport.school.com.schoolapp;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import frameworks.appsession.AppBaseApplication;
import frameworks.basemvp.AppBaseActivity;
import frameworks.basemvp.IPresenter;
import frameworks.retrofit.ResponseResolver;
import frameworks.retrofit.RestError;
import frameworks.retrofit.WebServicesWrapper;
import retrofit2.Response;
import transport.school.com.schoolapp.bean.ActiveRouteReply;
import transport.school.com.schoolapp.bean.RouteStudentList;
/**
 * Created by Naveen.Goyal on 11/29/2017.
 */
public class StudentAttendanceActivity extends AppBaseActivity {
    private RecyclerView recyclerView;
    private StudentAttendanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new StudentAttendanceAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        WebServicesWrapper.getInstance().getActiveRoute(AppBaseApplication.getApplication().getRoute(), new ResponseResolver<ActiveRouteReply>() {
            @Override
            public void onSuccess(ActiveRouteReply activeRouteReply, Response response) {
                if (activeRouteReply.getStudents().get(0).getE().equals("0") || activeRouteReply.getStudents().get(0).getM().equals("0")) {
                    startActivity(new Intent(StudentAttendanceActivity.this, StartRouteActivity.class));
                }
            }

            @Override
            public void onFailure(RestError error, String msg) {
            }
        });
        prepareStudentList();
    }

    @Override
    public int getViewToCreate() {
        return R.layout.activity_attendance;
    }

    @Override
    public IPresenter getPresenter() {
        return null;
    }

    private void prepareStudentList() {
        WebServicesWrapper.getInstance().getStudentListForRoute(AppBaseApplication.getApplication().getRoute(), new ResponseResolver<RouteStudentList>() {
            @Override
            public void onSuccess(RouteStudentList routeStudentList, Response response) {
                adapter.setStudentList(routeStudentList.getStudents());
            }

            @Override
            public void onFailure(RestError error, String msg) {
            }
        });
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
