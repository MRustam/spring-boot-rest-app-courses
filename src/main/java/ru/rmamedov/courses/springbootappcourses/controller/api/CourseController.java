package ru.rmamedov.courses.springbootappcourses.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.ICourseService;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private ICourseService iCourseService;

    @Autowired
    public CourseController(ICourseService iCourseService) {
        this.iCourseService = iCourseService;
    }

    // CRUD operations

    // Custom get all courses, sorted by rating.
    @GetMapping("/all")
    public List<Course> getAll() {
        return iCourseService.getAllByRating();
    }
    @GetMapping("/{id}")
    public Course getOneById(@PathVariable Long id) {
        return iCourseService.findOneById(id);
    }
    @PostMapping("/save")
    public Course saveOne(@RequestBody Course course) {
        return iCourseService.saveOne(course);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteOneById(@PathVariable Long id) {
        iCourseService.deleteOneById(id);
    }
    @PutMapping("/update")
    public Course updateById(@PathVariable Long id, @RequestBody Course course) {
        return iCourseService.updateOne(course);
    }

    //Find one by title.
    @GetMapping("/bytitle/{title}")
    public List<Course> findOneByTitle(@PathVariable String title) {
        return iCourseService.findOneByTitle(title);
    }

    //Filter by category.
    @GetMapping("/bycategory/{category}")
    public List<Course> findAllByCategory(@PathVariable String category) {
        return iCourseService.findByCategory(category);
    }

    //Get all reviews from current course.
    @GetMapping("/{id}/reviews")
    public List<Review> getReviewsOfCourse(@PathVariable Long id) {
        return iCourseService.getReviewsOfCurrentCourse(id);
    }
}
