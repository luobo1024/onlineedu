package com.luobo.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luobo.commonutils.JwtUtils;
import com.luobo.commonutils.Msg;
import com.luobo.commonutils.ordervo.CourseWebVoOrder;
import com.luobo.eduservice.client.OrdersClient;
import com.luobo.eduservice.entity.ChapterVo;
import com.luobo.eduservice.entity.EduCourse;
import com.luobo.eduservice.entity.frontvo.CourseFrontVo;
import com.luobo.eduservice.entity.frontvo.CourseWebVo;
import com.luobo.eduservice.service.EduChapterService;
import com.luobo.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.OrderComparator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private OrdersClient ordersClient;

    @PostMapping("getFrontCourseList/{current}/{limit}")
    public Msg getFrontCourseList(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> coursePage = new Page<>(current,limit);
        Map<String, Object> map = courseService.getCourseFrontList(coursePage, courseFrontVo);
        return Msg.success().data(map);
    }
    @GetMapping("getFrontCourseInfo/{courseId}")
    public Msg getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
       CourseWebVo courseWebVo =  courseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        boolean buyCourse = ordersClient.isBuyCourse(courseId,memberIdByJwtToken);
        return Msg.success().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoList).data("isBuy",buyCourse);
    }
    //根据课程id查询课程信息
    @GetMapping("getCourseInfo/{courseId}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String courseId){
        CourseWebVo baseCourseInfo = courseService.getBaseCourseInfo(courseId);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}
