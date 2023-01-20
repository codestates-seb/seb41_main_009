import { Navigate, useParams } from 'react-router-dom';
import styled from 'styled-components';

const Container = styled.div`
  width: var(--content-width);
  padding-right: calc(var(--body-width) - var(--content-width) - var(--sidebar-width));
`;

const PrivateRoute = ({ component: Component }) => {
  const user = sessionStorage.getItem('userId');
  const { userId } = useParams();

  if (user !== userId) {
    return <Navigate to={`/users/${userId}`} />;
  }

  return <Container>{Component}</Container>;
};

export default PrivateRoute;
