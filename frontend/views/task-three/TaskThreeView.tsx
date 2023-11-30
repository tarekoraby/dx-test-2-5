import {TaskThreeService, TaskOneAndTwoService} from 'Frontend/generated/endpoints.js';
import {AutoForm, AutoFormLayoutRendererProps, AutoGrid} from "@hilla/react-crud";
import TeamDtoModel from "Frontend/generated/com/example/application/dtos/TeamDtoModel.js";
import {GridColumn} from "@hilla/react-components/GridColumn.js";
import TeamDto from "Frontend/generated/com/example/application/dtos/TeamDto.js";
import {VerticalLayout} from "@hilla/react-components/VerticalLayout.js";
import {ComboBox} from "@hilla/react-components/ComboBox.js";
import EmployeeDtoModel from "Frontend/generated/com/example/application/dtos/EmployeeDtoModel.js";
import {SplitLayout} from "@hilla/react-components/SplitLayout.js";
import {useState} from "react";
import EmployeeDto from "Frontend/generated/com/example/application/dtos/EmployeeDto.js";



function EmployeeCountRenderer({ item }: { item: TeamDto }) {
    return (
        <span>{item.employees.length}</span>
    );
}

export default function TaskThreeView() {
    const [employees, setEmployees] = useState<EmployeeDto[] | null>(null);
    const [editedEmployee, setEditedEmployee] = useState<EmployeeDto | null>(null);
    function CustomLayoutRenderer({ children, form }: AutoFormLayoutRendererProps<EmployeeDtoModel>) {
        const fieldsMapping = new Map<string, JSX.Element>();
        children.forEach((field) => fieldsMapping.set(field.props?.propertyInfo?.name, field));
        // transform EmployeeDto[] | null to EmployeeDto[] | undefined for ComboBox
        const employeesArray = employees ? employees : undefined;
        return (
            <VerticalLayout className="gap-m p-m">
                <h4>Department Employees:</h4>
                <ComboBox
                    label="Employees"
                    item-label-path="name"
                    item-value-path="id"
                    items={employeesArray}
                    onValueChanged={(e) => {
                        const value = e.detail.value;
                        const employee = employeesArray?.find((employee) => employee.id === Number(value));
                        setEditedEmployee(employee ? employee : null);
                    }}
                />
                {fieldsMapping.get('id')}
                {fieldsMapping.get('name')}
                {fieldsMapping.get('email')}

            </VerticalLayout>
        );
    }

  return (
      <div>
          <h2>Task Three (not working currently!!)</h2>
          <h4>Different models in Grid and Form</h4>
          <br/>
          <SplitLayout>
              <AutoGrid style={{ width: '70%' }}
                  service={TaskOneAndTwoService}
                  model={TeamDtoModel}
                  visibleColumns={['name', 'email', 'employee-count-column']}
                  customColumns={[
                    <GridColumn key={'employee-count-column'} autoWidth renderer={EmployeeCountRenderer} header="Employees count" />
                  ]}
                  onActiveItemChanged={(e) => {
                      const item = e.detail.value;
                      setEmployees(item ? item.employees : null);
                  }}
              />
              <AutoForm
                  service={TaskThreeService}
                  model={EmployeeDtoModel}
                  item = {editedEmployee}
                  layoutRenderer={CustomLayoutRenderer}
              />
          </SplitLayout>
        </div>
  );
}
