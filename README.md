# hrmdatajpa
@Range(min = 1000000000, max = 9999999999L, message = "Employee Contact Number must be 10 digit")
    @Column(unique = true)
    private long empContactNumber;