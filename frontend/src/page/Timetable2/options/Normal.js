class Normal {
  constructor(lectureForms, lectures) {
    this.lectureForms = lectureForms;
    this.lectures = lectures;
    this.displayLectures = {};
  }

  execute() {

    if (this.lectureForms){
      this.lectureForms.forEach((lectureForm) => {
        const lecture = this.lectures[lectureForm];
  
        if (lecture.time) {
          var times = lecture.class_time.split(' ');
          let temp = null;
          var result = []
          times.forEach((text) => {
            if (!isNaN(text)){result.push(temp + String(text))}
            else {
              temp = text
            }
          })
          result.forEach((time) => {
            const weekday = time.split('')[0];
            const hours = time.split('')[1]
  
            if (weekday && hours) {
              const key = `${weekday}${hours}`;
              this.displayLectures = {
                ...this.displayLectures,
                [key]: lecture
              };
              };
            }
          );
        }
      });

    }
    
    return this.displayLectures;

  }
}

export default Normal;