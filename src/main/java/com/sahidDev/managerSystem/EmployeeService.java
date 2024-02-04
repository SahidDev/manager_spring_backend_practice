package com.sahidDev.managerSystem;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    public static final String COL_NAME = "users";

    public String saveEmployeeDetails(Employee employee) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME)
                .document(employee.getEmployeeId()).set(employee, SetOptions.mergeFields("firstName", "lastName"));
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Employee getEmployeeDetails(String lookUpId) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(lookUpId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Employee employee = null;

        if (document.exists()) {
            employee = new Employee(document.getString("firstName"), document.getString("lastName"),
                    document.getId());
            return employee;
        } else {
            return null;
        }
    }

    public List<Employee> getAllEmployeesDetails() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFirestore.collection(COL_NAME).listDocuments();
        List<Employee> employees = new ArrayList<Employee>();
        for (DocumentReference iter : documentReference) {
            ApiFuture<DocumentSnapshot> future = iter.get();
            DocumentSnapshot document = future.get();
            Employee item = new Employee(document.getString("firstName"), document.getString("lastName"),
                    document.getId());
            employees.add(item);
        }

        return employees;
    }

    public String updateEmployeeDetails(Employee person) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(person.getEmployeeId());
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            ApiFuture<WriteResult> collectionsApiFuture = documentReference.set(person,
                    SetOptions.mergeFields("firstName", "lastName"));
            return collectionsApiFuture.get().getUpdateTime().toString();
        } else {
            return "Employee ID with " + person.getEmployeeId() + " does not exists";
        }
    }

    public String deleteEmployee(String lookUpId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(lookUpId).delete();
        return "Document with Employee ID " + lookUpId + " has been deleted";
    }
}
