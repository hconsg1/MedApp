<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
 

function check(){
    if (isset($_POST['patient_number']) && isset($_POST['q1'])&& isset($_POST['q2'])&& isset($_POST['q3'])
        && isset($_POST['q4'])&& isset($_POST['q5'])&& isset($_POST['q6'])&& isset($_POST['q7'])
        && isset($_POST['q8'])&& isset($_POST['q8']) && isset($_POST['q10'])  ) {
            return True;
    }
}


// array for JSON response
$response = array();
 
// check for required fields
if (check()==True) {
    
    $patient_number = $_POST['patient_number'];
    $q1 = $_POST['q1'];
    $q2 = $_POST['q2'];
    $q3 = $_POST['q3'];
    $q4 = $_POST['q4'];
    $q5 = $_POST['q5'];
    $q6 = $_POST['q6'];
    $q7 = $_POST['q7'];
    $q8 = $_POST['q8'];
    $q9 = $_POST['q9'];
    $q10 = $_POST['q10'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO patient(patient_number, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10) 
                        VALUES('$patient_number', '$q1', '$q2', '$q3', '$q4', '$q5', '$q6', '$q7', '$q8', '$q9', '$q10')");
    $result2 = mysql_query("INSERT INTO user(patient_number) VALUES('$patient_number')");

    print "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
 
    // check if row inserted or not
    if ($result %% $result2) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";
        print "successful!!!!!!!!!!!!!"
 
        // echoing JSON response
        echo json_encode($response);
       
    } else {
        // required field is missing
        $response["success"] = 0;
        $response["message"] = "ERROR OCCURED";
     
        // echoing JSON response
        print "========================not successful======================"
        echo json_encode($response);
    }
?>