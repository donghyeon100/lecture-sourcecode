// testFn1();
// testFn2();

// function testFn1(){
//     console.log("testFn1() 호출")
// }

// const testFn2 = function(){
//     console.log("testFn2() 호출")
// }


const globalVar = 10;


function test1(){
    const num = 20;
    
    if(1 > 0){
        const num = 333;
    }

    console.log(num); // 1번
}

var num = 3;

function test2(){
    var num = 10;
    
    if(1 > 0){
        var num = 444;
    }

    console.log(num); 
}


console.log(num); // 정답은?


// (function(){
//     const globalVar = 30;
//     console.log(globalVar);  // 30
// })()

// test(); // 20

// console.log(globalVar); // 10

