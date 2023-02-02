    console.log("Hey");
    const stikkord = ['Dette', 'var', 'en', 'stilig', 'øving'];
    let j = 0;
    let bool = 0
   
    const cont = document.getElementById("stikkord-container");
    let ul = document.createElement('ul');
    ul.setAttribute('id','test-list');
    console.log(ul)
    console.log(cont)
    function alertFunction() 
    {
        alert('Du trykket på knappen!');
    }

    function changeVisibility() {
        let x = document.getElementById("demo");
        if(x.style.display == "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
      }
    
    function incrementNumber() {
        document.getElementById("number").innerHTML = ++j;
        if (j > 29) {
            j = 0
        }
    }

    function makeUL(array) {
        for (let i=0; i<array.length;i++) {
            let li = document.createElement('li')
            li.appendChild(document.createTextNode(stikkord[i]))
            ul.appendChild(li)
        }
        return ul
    }

    function clearList(elementID)
    {
        document.getElementById(elementID).innerHTML = "";
    }

    function fillList() {
        console.log(bool)
        if (bool == 0) {
            document.getElementById("stikkord-container").appendChild(makeUL(stikkord))
            bool++
            console.log(bool)     
        }
        else {
            clearList("test-list")
            bool = 0
            console.log(bool)
        }
    }


      