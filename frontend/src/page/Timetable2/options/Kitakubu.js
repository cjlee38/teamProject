class Kitakubu {
  constructor(lectureForms, lectures, credit, click) {
    this.lectureForms = lectureForms;
    this.lectures = lectures;
    this.credit = credit;
    this.displayLectures = {};
    this.handleClick = click

  }

  // TODO: 강의 배치 로직 구현
  execute() {
    const result = []

    this.lectureForms.forEach((lectureForm) => {
      const lecture = this.lectures[lectureForm];

      const hours=['7', '8','9', '10', '11', '12', '13']
      const weekdays=['월', '화', '수', '목', '금']
            //const key = `${weekday}${hours}`;
           
            for(let i=0; i<7; i++){
              if ( hours) {
                for(let j=0; j<5; j++){
                const key = `${weekdays[j]}${hours[i]}`;
                result.push(key)
                this.displayLectures = {
                  ...this.displayLectures,
                  [key]: {
                    name: lecture.name,
                    professor: lecture.professor,
                    location: lecture.location,
                    isRequired: lecture.isRequired,
                    
                    hours
                  }
                };
              }}
            };
            
        }); 
    
        return this.displayLectures;
  
 }
  };
export default Kitakubu;