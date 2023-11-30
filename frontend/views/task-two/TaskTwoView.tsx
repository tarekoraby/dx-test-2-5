import {TaskOneAndTwoService} from 'Frontend/generated/endpoints.js';
import {AutoCrud, AutoForm, AutoFormLayoutRendererProps} from "@hilla/react-crud";
import TeamDtoModel from "Frontend/generated/com/example/application/dtos/TeamDtoModel.js";
import {GridColumn} from "@hilla/react-components/GridColumn.js";
import TeamDto from "Frontend/generated/com/example/application/dtos/TeamDto.js";
import {TextField} from "@hilla/react-components/TextField.js";
import {VerticalLayout} from "@hilla/react-components/VerticalLayout.js";




function EmployeeCountRenderer({ item }: { item: TeamDto }) {
    return (
        <span>{item.employees.length}</span>
    );
}
function formRenderer({ children, form }: AutoFormLayoutRendererProps<TeamDtoModel>) {
    const { field, model } = form;
    const employeesArray = model.employees.valueOf();
    return (
        <VerticalLayout className="gap-m p-m">
            <h4>Department Information:</h4>
            <TextField label="Name" {...field(model.name)} />
            <TextField label="Email" {...field(model.email)} />
            <span>Employees count: {employeesArray.length}</span>
        </VerticalLayout>
    );
}

export default function TaskTwoView() {

  return (
      <div>
          <h2>Task Two</h2>
          <h4>Advanced customization of Grid cols and form fields </h4>
          <br/>
          <AutoCrud service={TaskOneAndTwoService} model={TeamDtoModel}
              gridProps={{
                  visibleColumns: ['name', 'email', 'employee-count-column'],
                  customColumns: [
                      <GridColumn key={'employee-count-column'} autoWidth renderer={EmployeeCountRenderer} header="Employees count" />
                  ]}}
              formProps={{
                  visibleFields: ['id', 'name', 'email'],
                  layoutRenderer: formRenderer
              }}
          />
        </div>
  );
}
