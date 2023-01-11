import styled from 'styled-components';

const Container = styled.div`
  width: 1056px;
`;

const PublicRoute = ({ component: Component }) => {
  return <Container>{Component}</Container>;
};

export default PublicRoute;
