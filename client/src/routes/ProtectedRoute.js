import { Navigate } from 'react-router-dom';
import styled from 'styled-components';

const Container = styled.div`
  width: var(--content-width);
  padding-right: calc(var(--body-width) - var(--content-width) - var(--sidebar-width));
`;

const ProtectedRoute = ({ component: Component }) => {
  const user = sessionStorage.getItem('userId');

  if (!user) {
    return <Navigate to="/login" />;
  }

  return <Container>{Component}</Container>;
};

export default ProtectedRoute;
