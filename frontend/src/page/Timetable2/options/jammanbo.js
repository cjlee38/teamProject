class snorlax {
    constructor(lectureForms, lectures, credit) {
      this.lectureForms = lectureForms;
      this.lectures = lectures;
      this.credit = credit;
      this.displayLectures = {};
    }
  
    // TODO: 강의 배치 로직 구현
    execute() {
      

      const hours=['1', '2','3']
      const weekdays=['월', '화', '수', '목', '금']
            // const key = `${weekday}${hours}`;
           
            for(let i=0; i<hours.length; i++){
              if ( hours) {
                for(let j=0; j<5; j++){
                const key = `${weekdays[j]}${hours[i]}`;
                // result.push(key)
                this.displayLectures = {
                  ...this.displayLectures,
                  [key]: {
                    hours
                  }
                };
              }}
            };
      if (this.lectureForms){
      const result = []
  
      this.lectureForms.forEach((lectureForm) => {
        const lecture = this.lectures[lectureForm];
        if (lecture.time) {
          var times = lecture.time.split(' ');
          let temp = null;
          var result = []
          times.forEach((text) => {
            if (!isNaN(text)){result.push(temp + String(text))}
            else {
              temp = text
            }
          })
          console.log(result)
          result.forEach((time) => {
            const weekday = time.split('')[0];
            const hours = time.split('')[1]
  
            if (weekday && hours) {
              const key = `${weekday}${hours}`;
  
              this.displayLectures = {
                ...this.displayLectures,
                [key]: {
                  name: lecture.name,
                  professor: lecture.professor,
                  location: lecture.location,
                  isRequired: lecture.isRequired,
                  weekday,
                  hours
                }
              };
            }
          });
        }
  
              
          }); 
        }
          return this.displayLectures;
    
   


  
      return this.displayLectures;
    }
  }
  
  export default snorlax;
  